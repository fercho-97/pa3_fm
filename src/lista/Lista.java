package lista;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

    default Lista<T> invertir(){

        if (this.isEmpty()) {

            return NIL;
        } else {

            return tail().invertir().append(head());
        }

    }

    default Lista<T> drop(Integer elem) {

            if (!(elem == 0)) {
                return tail().drop(elem - 1);
            }else if (elem==0) {

                return this;

        } else {
                return tail();
            }


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

    default Lista<T> takeWhile(Predicate<T> p) {

        if (isEmpty()){

            return  NIL;
        }else {

            if (p.test(head())){

                return Lista.of(head(), tail().takeWhile(p));
            }else {

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


    static<T> Function<Function<T, T>, Function<Function<T, T>,Function<T, T>>> compPoli (){

        return f->g->x-> g.apply(f.apply(x));

    }



    static<T,U,V> Function<Function<T, U>, Function<Function<U, V>,Function<T, V>>> compPoli2 (){

        return f->g->x-> g.apply(f.apply(x));


    }



    static<T> Function<T, T> comp(Function<T, T> f, Function<T, T> g) {

        return new Function<T, T>() {
            @Override
            public T apply(T x) {

                return g.apply(f.apply(x));
            }
        };
    }



    static<T,U,V> Function<T, V> compPolFun(Function<T, U> f, Function<U, V> g) {

        return new Function<T, V>() {
            @Override
            public V apply(T x) {

                return g.apply(f.apply(x));
            }
        };
    }


    default Integer size(){
        if (isEmpty()){

            return 0;

        }else {

            return 1+ tail().size();
        }


    }
    public static <U> Lista<U> concatenar(Lista<U> lista1, Lista<U> lista2) {
        return lista1.isEmpty() ? lista2 : Lista.of(lista1.head(), concatenar(lista1.tail(), lista2));
    }




}

