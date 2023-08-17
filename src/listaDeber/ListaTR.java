package listaDeber;

import recursion.TailCall;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public sealed interface ListaTR<T> permits ListaTRH, Cons, Nil {

    ListaTR NIL = new Nil();

    T head();

    ListaTR<T> tail();


    boolean isEmpty();

    static <T> ListaTR<T> of(T h, ListaTR<T> t) {
        return new Cons<>(h, t);
    }

    static <T> TailCall<ListaTR<T>> ofTH(T h, ListaTR<T> t) {
        return TailCall.ret(new Cons<>(h, t));
    }

    static <T> ListaTR<T> of(T... elems) {

        ListaTR<T> ret = ListaTR.NIL;
        for (int i = elems.length - 1; i >= 0; i--) {
            T h = elems[i];
            var tmp = ListaTR.of(h, ret);
            ret = tmp;

        }
        return ret;
    }

    static <T> ListaTR<T> appendTRAux(ListaTR<T> orginal, ListaTR<T> acum, T elem) {
        if (acum.isEmpty()) {
            return invertirTR(ListaTR.of(elem, orginal), ListaTR.NIL);
        } else {
            return appendTRAux(ListaTR.of(orginal.head(), acum), acum.tail(), elem);
        }
    }


    static <T> ListaTR<T> appendTR(T elem, ListaTR<T> list) {
        return appendTRAux(list, ListaTRH.of(), elem);
    }

    static <T> ListaTR<T> preppendTRAux(T val, ListaTR<T> original) {
        if (original.isEmpty()) {
            return ListaTRH.of(val);
        } else {
            return ListaTR.of(val, original);
        }
    }

    static <T> ListaTR<T> preppendTR(T val, ListaTR<T> original) {
        return preppendTRAux(val, original);

    }

    static <T> ListaTR<T> invertirTRAux(ListaTR<T> list, ListaTR<T> acum) {
        if (list.isEmpty()) {
            return acum;
        } else {
            return invertirTRAux(list.tail(), preppendTR(list.head(), acum));
        }
    }

    static <T> ListaTR<T> invertirTR(ListaTR<T> list, ListaTR<T> acum) {

        return invertirTRAux(list, acum);
    }


    static <T> ListaTR<T> dropTRAux(ListaTR<T> list, Integer elem) {
        if (!(elem == 0)) {

            return dropTRAux(list.tail(), elem - 1);

        } else if (elem == 0) {

            return list;
        } else {
            return list.tail();
        }

    }

    static <T> ListaTR<T> dropTR(ListaTR<T> list, Integer elem) {

        return dropTRAux(list, elem);
    }

    static <T> ListaTR<T> dropWhileTRAux(ListaTR<T> list, Predicate<T> p) {

        if (list.isEmpty()) {
            return ListaTR.NIL;
        } else {

            if (p.test(list.head())) {

                return dropWhileTRAux(list.tail(), p);
                //return tail().dropWhile(p);
            } else {

                return ListaTR.of(list.head(), dropWhileTRAux(list.tail(), p));

            }
        }
    }

    static <T> ListaTR<T> dropWhileTRH(ListaTR<T> list, Predicate<T> p) {

        return dropWhileTRAux(list, p);
    }

    static <T> ListaTR<T> takeTRAux(ListaTR<T> list, ListaTR<T> acum, Integer elem) {


        if (list.isEmpty()) {

            return ListaTRH.NIL;
        } else {
            if (elem == 0) {

                return acum;
            } else {
                return takeTRAux(list.tail(), preppendTR(list.head(), acum), elem - 1);

            }
        }

    }

    static <T> ListaTR<T> takeTR(ListaTR<T> list, ListaTR<T> acum, Integer elem) {

        return invertirTR(takeTRAux(list, acum, elem), NIL);
    }


    static <T> ListaTR<T> takeWhileTRAux(ListaTR<T> list, ListaTR<T> acum, Predicate<T> p) {

        if (list.isEmpty()) {

            return acum;
        } else {

            if (p.test(list.head())) {

                return takeWhileTRAux(list.tail(), preppendTR(list.head(), acum), p);

            } else {

                return takeWhileTRAux(list.tail(), acum, p);
            }
        }


    }

    static <T> ListaTR<T> takeWhileTRH(ListaTR<T> list, ListaTR<T> acum, Predicate<T> p) {

        return invertirTR(takeWhileTRAux(list, acum, p), NIL);

        // return invertirTRH(takeTRHAux(list, acum, elem).eval(), NIL);
    }

    static <T> ListaTR<T> removeTRAux(ListaTR<T> list, ListaTR<T> acum, T elem) {

        if (list.isEmpty()) {

            return acum;
        } else {
            if (list.head().equals(elem)) {

                return removeTRAux(list.tail(), acum, elem);
            } else {


                return removeTRAux(list.tail(), preppendTR(list.head(), acum), elem);

            }
        }

    }

    static <T> ListaTR<T> removeTR(ListaTR<T> list, T elem) {

        return invertirTR(removeTRAux(list, ListaTRH.of(), elem), NIL);

    }


    static <T> ListaTR<T> concatenarTRAux(ListaTR<T> lista1, ListaTR<T> lista2) {
        if (lista1.isEmpty()) {
            return lista2;
        } else {

            return concatenarTRAux(lista1.tail(), preppendTR(lista1.head(), lista2));
        }
    }

    static <T> ListaTR<T> concatenarTR(ListaTR<T> lista1, ListaTR<T> lista2) {
        return concatenarTRAux(invertirTR(lista1, ListaTRH.of()), lista2);
    }

    static <T> ListaTR<T> replaceTRAux(ListaTR<T> list, T elem, T newElem, ListaTR<T> acum) {

        if (list.isEmpty()) {
            return acum;

        } else if (list.head().equals(elem)) {

            return replaceTRAux(list.tail(), elem, newElem, preppendTR(newElem, acum));

        } else {


            return replaceTRAux(list.tail(), elem, newElem, preppendTR(list.head(), acum));
        }
    }

    static <T> ListaTR<T> replaceTR(ListaTR<T> list, T elem, T newElem) {
        return invertirTR(replaceTRAux(list, elem, newElem, ListaTR.of()), NIL);

    }

    static <T> Integer sizeTRAux(ListaTR<T> list, Integer intit) {
        if (list.isEmpty()) {
            return intit;
        } else {
            return sizeTRAux(list.tail(), intit + 1);
        }
    }

    static <T> Integer sizeTR(ListaTR<T> list) {

        return sizeTRAux(list, 0);
    }


    static <T> Optional<T> containsTRAux(ListaTR<T> original, ListaTR<T> acum, T elem) {
        if (original.isEmpty()) {
            return Optional.empty();
        }
        if (original.head().equals(elem)) {
            return Optional.of(original.head());
        } else {
            return containsTRAux(original.tail(), preppendTR(original.head(), acum), elem);
        }
    }

    static <T> Optional<T> containsTR(ListaTR<T> original, T elem) {
        return (Optional<T>) containsTRAux(original, NIL, elem);
    }

    static <T> void forEach(ListaTR<T> original, Consumer<T> fn) {
        forEachTR(original, ListaTR.NIL, fn);
    }

    static <T> void forEachTR(ListaTR<T> original, ListaTR<T> acum, Consumer<T> fn) {
        if (original.isEmpty()) {
            preppendTRAux(original.head(), acum).head();
        } else {
            fn.accept(original.head());
            forEachTR(acum.tail(), acum, fn);
        }
    }

    static <T> TailCall<T> forEachP(ListaTR<T> lista, Consumer<T> fn) {
        return forEachTRHeap(lista, ListaTRH.NIL, fn);
    }

    static <T> TailCall<T> forEachTRHeap(ListaTR<T> acc, ListaTR<T> retorno, Consumer<T> fn) {
        if (acc.isEmpty()) {
            return TailCall.ret(appendTR(acc.head(), retorno).head());//retorno.append(acc.head()).eval().head()
        } else {
            fn.accept(acc.head());
            return TailCall.sus(() -> forEachTRHeap(acc.tail(), retorno, fn));
        }
    }

    public static void main(String[] args) {

        ListaTR<Integer> original = ListaTR.of(1, 2, 3, 4, 5);
        ListaTR<Integer> original2 = ListaTR.of(11, 12, 13, 14, 15);

        var l1 = appendTR(6, original);
        System.out.println("Append= " + l1);

        var l2 = preppendTR(0, original);
        System.out.println("Preppend= " + l2);


        var l3 = invertirTR(original, NIL);
        System.out.println("Invertir= " + l3);

        var l = dropTR(original, 3);
        System.out.println("Drop= " + l);

        var l4 = dropWhileTRH(original, x -> x % 2 == 0);
        System.out.println("DropWhile= " + l4);

        var l5 = takeTR(original, NIL, 2);
        System.out.println("Take= " + l5);

        var l6 = takeWhileTRH(original, NIL, x -> x % 2 == 0);
        System.out.println("TakeWhile= " + l6);

        var l7 = removeTR(original, 4);
        System.out.println("remove= " + l7);

        var l8 = concatenarTR(original, original2);
        System.out.println("concat= " + l8);

        var l9 = replaceTR(original, 3, 20);
        System.out.println("replace= " + l9);

        var l10 = sizeTR(original);
        System.out.println("Size= " + l10);

        var l11 = containsTR(original, 3);
        System.out.println("Contains= " + l11);

        var l12 = forEachP(original, x -> System.out.print(x)).eval();
        System.out.println(l12);

    }
}
