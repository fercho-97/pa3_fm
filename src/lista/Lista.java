package lista;

import recursion.TailCall;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public sealed interface Lista<T> permits Cons, Nil {
    Lista NIL = new Nil();

    T head();

    Lista<T> tail();

    boolean isEmpty();

    // public static final Lista NIL = new Nil();

    static <T> Lista<T> of(T h, Lista<T> t) {
        return new Cons<>(h, t);
    }

    static <T> Lista<T> of(Lista<T> t, Lista<T> t2, T h) {
        return concatenar(t, t2).append(h);
    }

    static <T> Lista<T> of(Lista<T> t, T h, Lista<T> t2) {

        return concatenar(t.append(h), t2);
    }

    static <T> Lista<T> of(T h, Lista<T> t, Lista<T> t2) {
        return concatenar(new Cons<>(h, t), t2);
    }

    static <T> TailCall<Lista<T>> ofTH(T h, Lista<T> t) {
        return TailCall.ret(new Cons<>(h, t));
    }

    static <T> Lista<T> of(T... elems) {

        Lista<T> ret = Lista.NIL;
        for (int i = elems.length - 1; i >= 0; i--) {
            T h = elems[i];
            var tmp = Lista.of(h, ret);
            ret = tmp;

        }
        return ret;
    }
/*
   default String toString1(){
        StringBuilder sb = new StringBuilder();
        var tmp = this;
        while (!tmp.isEmpty()){

            sb.append(tmp.head());
            sb.append(", ");
            tmp = tmp.tail();
        }
        sb.append("NIL");
         return sb.toString();
   }

 */

    default Lista<T> append(T val) {

        if (this == NIL) {
            return Lista.of(val);
        } else {

            return Lista.of(head(), tail().append(val));
        }

    }

    default TailCall<Lista<T>> appendTRHAux(Lista<T> acc, T elem) {
        if (acc.isEmpty()) {
            return TailCall.ret(Lista.of(elem, acc));
        } else {
            return TailCall.ret(Lista.of(acc.head(), acc.tail().appendTRHAux(acc.tail(), elem).eval()));
        }
    }
    default Lista<T> appendTRH(T elem) {
        return appendTRHAux(this, elem).eval();
    }



    static <T>  TailCall<Lista<T>> appendTH(Lista<T> original ,Lista<T> acum, T val) {

        if (original == NIL) {
            return TailCall.ret(acum.preppend(val));
        } else {

            Supplier<TailCall<Lista<T>>> ret = () -> appendTH(original.tail(), acum.preppend(original.head()), val);
            return TailCall.sus(ret);

        }

    }



    default Lista<T> preppend(T val) {

        if (this == NIL) {
            return Lista.of(val);
        } else {
            return Lista.of(val, this);
        }

    }

    public static <U> Lista<U> concatenar2(Lista<U> lista1, Lista<U> lista2) {
        return lista1.isEmpty() ? lista2 : Lista.of(lista1.head(), concatenar2(lista1.tail(), lista2));
    }


    default Lista<T> removeAll(T val) {
        var tmp = this;
        var ret = NIL;
        while (tmp != NIL) {

            if (!(tmp.head().equals(val))) {
                ret = ret.append(tmp.head());

            }
            tmp = tmp.tail();

        }

        return ret;

    }

    default Lista<T> remove(T elem) {

        if (this.isEmpty()) {

            return NIL;
        } else {
            if (head().equals(elem)) {

                return tail();
            } else {
                return Lista.of(head(), tail().remove(elem));
            }
        }

    }

    default Lista<T> invertir() {

        if (this.isEmpty()) {

            return NIL;
        } else {

            return tail().invertir().append(head());
        }

    }

//    static<T> TailCall<Lista<T>> invertirTH(Lista<T> list, Lista<T> acum) {
//        if (list.isEmpty()) {
//            return TailCall.ret(acum);
//        } else {
//            Supplier<TailCall<Lista<T>>> ret = () -> invertirTH(list.tail(), acum.preppend(list.head()));
//            return TailCall.sus(ret);
//        }
//    }


    default Lista<T> drop(Integer elem) {

        if (!(elem == 0)) {
            return tail().drop(elem - 1);
        } else if (elem == 0) {

            return this;

        } else {
            return tail();
        }
    }


    static<T> TailCall<Lista<T>> dropTHAux(Lista<T> list, Integer elem) {
        if (!(elem == 0)) {

            Supplier<TailCall<Lista<T>>> ret = () -> dropTHAux(list.tail(),elem - 1);
            return TailCall.sus(ret);

        }else if (elem == 0) {

            return TailCall.ret(list);
        } else {
            return TailCall.ret(list.tail());
        }

    }

    default Lista<T> dropTH(Integer elem){
        return dropTHAux(this,  elem).eval();
    }



    default Lista<T> dropWhile(Predicate<T> p) {

           /* if (isEmpty()) {
                return NIL;
            } else {

                if (p.test(head())){
                    return tail().dropWhile(p);
                }else{
                    return Lista.of(head(), tail().dropWhile(p));
                }
            }
*/

        return isEmpty() ? NIL
                : p.test(head()) ? tail().dropWhile(p)
                : Lista.of(head(), tail().dropWhile(p));
    }

    static<T> TailCall<Lista<T>> dropWhileTHAux(Lista<T> list,Predicate<T> p) {

        if (list.isEmpty()) {
            return TailCall.ret(NIL);
        } else {

            if (p.test(list.head())){

                Supplier<TailCall<Lista<T>>> ret = ()-> dropWhileTHAux(list.tail(), p);
                return  TailCall.sus(ret);
                //return tail().dropWhile(p);
            }else{
                Supplier<TailCall<Lista<T>>> ret1 = ()-> Lista.ofTH(list.head(), dropWhileTHAux(list.tail(),p).eval());

                return  TailCall.sus(ret1);

            }
        }
    }

    default Lista<T> dropWhileTH(Predicate<T> p) {

        return dropWhileTHAux(this, p).eval();
    }


    default Lista<T> take(Integer elem) {
        if (this.isEmpty()) {

            return NIL;
        } else {
            if (elem == 0) {
                return Lista.of();
            } else {
                return tail().take(elem - 1).preppend(head());
            }
        }
    }

    static<T> TailCall<Lista<T>> takeTHAux(Lista<T> list, Lista<T> acum,Integer elem) {

        if (list.isEmpty()) {

            return TailCall.ret(NIL);
        } else {
            if (elem == 0) {
                return TailCall.ret(Lista.of());
            } else {
                Supplier<TailCall<Lista<T>>> ret = ()-> takeTHAux(list.tail(), acum.preppend(list.head()), elem-1);
                return  TailCall.sus(ret);

            }
        }

    }
    default Lista<T> takeTH(Integer elem, Lista<T> acum) {

        return  takeTHAux(this, acum,elem).eval();
    }



        default Lista<T> takeWhile(Predicate<T> p) {

        if (isEmpty()) {

            return NIL;
        } else {

            if (p.test(head())) {

                return Lista.of(head(), tail().takeWhile(p));
            } else {

                return tail().takeWhile(p);
            }
        }


    }

    static int suma(Lista<Integer> lista) {

        if (lista.isEmpty()) {

            return 0;
        } else {
            return lista.head() + suma(lista.tail());

        }


    }

    static int max(Lista<Integer> lista) {

        return maxAux(lista, lista.head());
    }

    static int maxAux(Lista<Integer> lista, Integer m) {

        if (lista.isEmpty()) {
            return m;
        } else {

            if (m <= lista.head()) {

                m = lista.head();

                return maxAux(lista.tail(), m);
            } else {

                return maxAux(lista.tail(), m);

            }
        }

    }


    static int minAux(Lista<Integer> lista, Integer m) {

        if (lista.isEmpty()) {
            return m;
        } else {

            if (m <= lista.head()) {


                return minAux(lista.tail(), m);
            } else {
                m = lista.head();
                return minAux(lista.tail(), m);

            }
        }

    }

    static int min(Lista<Integer> lista) {

        return minAux(lista, lista.head());
    }


    static <T> Function<Function<T, T>, Function<Function<T, T>, Function<T, T>>> compPoli() {

        return f -> g -> x -> g.apply(f.apply(x));

    }


    static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> compPoli2() {

        return f -> g -> x -> g.apply(f.apply(x));


    }


    static <T> Function<T, T> comp(Function<T, T> f, Function<T, T> g) {

        return new Function<T, T>() {
            @Override
            public T apply(T x) {

                return g.apply(f.apply(x));
            }
        };
    }


    static <T, U, V> Function<T, V> compPolFun(Function<T, U> f, Function<U, V> g) {

        return new Function<T, V>() {
            @Override
            public V apply(T x) {

                return g.apply(f.apply(x));
            }
        };
    }


    default Integer size() {
        if (isEmpty()) {

            return 0;

        } else {

            return 1 + tail().size();
        }


    }

    public static <U> Lista<U> concatenar(Lista<U> lista1, Lista<U> lista2) {
        return lista1.isEmpty() ? lista2 : Lista.of(lista1.head(), concatenar(lista1.tail(), lista2));
    }

    default <U> Lista<U> map(Function<T, U> fn) {

        if (this.isEmpty()) {

            return Lista.NIL;
        } else {
            var tmp = tail().map(fn);
            var newCab = fn.apply(this.head());
// return Lista.of(newCab, tmp);

            return tmp.preppend(newCab);
        }
    }

    default <U> Lista<U> mapIt(Function<T, U> fn) {

        var tmp = this;

        Lista<U> retTmp = Lista.NIL;

        while (!tmp.isEmpty()) {

            T elem = tmp.head();
            U newElem = fn.apply(elem);

            retTmp = Lista.of(newElem, retTmp);

            tmp = tmp.tail();


        }
        return retTmp;
    }

    default Lista<T> inv() {

        var tmp = this;

        Lista<T> retTmp = Lista.NIL;

        while (!tmp.isEmpty()) {


            retTmp = Lista.of(tmp.head(), retTmp);

            tmp = tmp.tail();


        }
        return retTmp;
    }

    static <T> T reduce(List<T> ls, Function<T, Function<T, T>> fn) {

        T acum = ls.get(0);
        for (int i = 1; i < ls.size(); i++) {

            T elem = ls.get(i);
            acum = fn.apply(acum).apply(elem);

        }
        return acum;
    }

    default T reduce2(T identity, Function<T, Function<T, T>> fn) {

        T acum = identity;

        var tmp = this;

        while (!tmp.isEmpty()) {
            acum = fn.apply(tmp.head()).apply(acum);

            tmp = tmp.tail();
        }

        return acum;
    }

    default <U> U foldLeft(U identity, Function<U, Function<T, U>> fn) {

        U acum = identity;

        var tmp = this;

        while (!tmp.isEmpty()) {
            acum = fn.apply(acum).apply(tmp.head());

            tmp = tmp.tail();
        }

        return acum;
    }

    default <U> U foldRight(U identity, Function<T, Function<U, U>> fn) {

        if (this.isEmpty()) {
            return identity;

        } else {

            T elem = this.head();
            U tmp = this.tail().foldRight(identity, fn);

            return fn.apply(elem).apply(tmp);

        }


    }


}

