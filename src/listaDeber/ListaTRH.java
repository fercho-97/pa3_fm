package listaDeber;

import recursion.TailCall;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;


public non-sealed interface ListaTRH<T> extends ListaTR<T> {


    ListaTRH NIL = new Nil();

    T head();

    ListaTRH<T> tailH();


    static <T> ListaTRH<T> of(T h, ListaTRH<T> t) {
        return new Cons<>(h, t);
    }

    static <T> TailCall<ListaTRH<T>> ofTH(T h, ListaTRH<T> t) {
        return TailCall.ret(new Cons<>(h, t));
    }

    static <T> ListaTRH<T> of(T... elems) {

        ListaTRH<T> ret = ListaTRH.NIL;
        for (int i = elems.length - 1; i >= 0; i--) {
            T h = elems[i];
            var tmp = ListaTRH.of(h, ret);
            ret = tmp;

        }
        return ret;
    }


    static <T> TailCall<ListaTRH<T>> appendTRHAux(ListaTRH<T> orginal, ListaTRH<T> acum, T elem) {
        if (acum.isEmpty()) {
            return TailCall.ret(invertirTRH(ListaTRH.of(elem, orginal), ListaTRH.NIL));
        } else {
            return TailCall.sus(() -> appendTRHAux(ListaTRH.of(orginal.head(), acum), acum.tailH(), elem));
        }
    }


    static <T> ListaTRH<T> appendTRH(T elem, ListaTRH<T> list) {
        return appendTRHAux(list, ListaTRH.of(), elem).eval();
    }

    static <T> TailCall<ListaTRH<T>> preppendTRHAux(T val, ListaTRH<T> original) {
        if (original.isEmpty()) {
            return TailCall.ret(ListaTRH.of(val));
        } else {
            return TailCall.ret(ListaTRH.of(val, original));
        }
    }

    static <T> ListaTRH<T> preppendTRH(T val, ListaTRH<T> original) {
        return preppendTRHAux(val, original).eval();

    }

    static <T> TailCall<ListaTRH<T>> invertirTRHAux(ListaTRH<T> list, ListaTRH<T> acum) {
        if (list.isEmpty()) {
            return TailCall.ret(acum);
        } else {
            Supplier<TailCall<ListaTRH<T>>> ret = () ->
                    invertirTRHAux(list.tailH(), preppendTRH(list.head(), acum));
            return TailCall.sus(ret);
        }
    }

    static <T> ListaTRH<T> invertirTRH(ListaTRH<T> list, ListaTRH<T> acum) {

        return invertirTRHAux(list, acum).eval();
    }


    static <T> TailCall<ListaTRH<T>> dropTRHAux(ListaTRH<T> list, Integer elem) {
        if (!(elem == 0)) {

            Supplier<TailCall<ListaTRH<T>>> ret = () ->
                    dropTRHAux(list.tailH(), elem - 1);
            return TailCall.sus(ret);

        } else if (elem == 0) {

            return TailCall.ret(list);
        } else {
            return TailCall.ret(list.tailH());
        }

    }

    static <T> ListaTRH<T> dropTRH(ListaTRH<T> list, Integer elem) {

        return dropTRHAux(list, elem).eval();
    }

    static <T> TailCall<ListaTRH<T>> dropWhileTRHAux(ListaTRH<T> list, Predicate<T> p) {

        if (list.isEmpty()) {
            return TailCall.ret(ListaTRH.NIL);
        } else {

            if (p.test(list.head())) {

                Supplier<TailCall<ListaTRH<T>>> ret = () ->
                        dropWhileTRHAux(list.tailH(), p);
                return TailCall.sus(ret);
                //return tail().dropWhile(p);
            } else {
                Supplier<TailCall<ListaTRH<T>>> ret1 = () ->
                        ListaTRH.ofTH(list.head(), dropWhileTRHAux(list.tailH(), p).eval());

                return TailCall.sus(ret1);

            }
        }
    }

    static <T> ListaTRH<T> dropWhileTRH(ListaTRH<T> list, Predicate<T> p) {

        return dropWhileTRHAux(list, p).eval();
    }

    static <T> TailCall<ListaTRH<T>> takeTRHAux(ListaTRH<T> list, ListaTRH<T> acum, Integer elem) {


        if (list.isEmpty()) {

            return TailCall.ret(ListaTRH.NIL);
        } else {
            if (elem == 0) {

                return TailCall.ret(acum);

            } else {

                Supplier<TailCall<ListaTRH<T>>> ret = () ->
                        takeTRHAux(list.tailH(), preppendTRH(list.head(), acum), elem - 1);
                return TailCall.sus(ret);

            }
        }

    }

    static <T> ListaTRH<T> takeTRH(ListaTRH<T> list, ListaTRH<T> acum, Integer elem) {

        return invertirTRH(takeTRHAux(list, acum, elem).eval(), NIL);
    }


    static <T> TailCall<ListaTRH<T>> takeWhileTRHAux(ListaTRH<T> list, ListaTRH<T> acum, Predicate<T> p) {

        if (list.isEmpty()) {

            return TailCall.ret(acum);
        } else {

            if (p.test(list.head())) {

                Supplier<TailCall<ListaTRH<T>>> ret = () ->
                        takeWhileTRHAux(list.tailH(), preppendTRH(list.head(), acum), p);
                return TailCall.sus(ret);

            } else {

                Supplier<TailCall<ListaTRH<T>>> ret1 = () ->
                        takeWhileTRHAux(list.tailH(), acum, p);
                return TailCall.sus(ret1);
            }
        }


    }

    static <T> ListaTRH<T> takeWhileTRH(ListaTRH<T> list, ListaTRH<T> acum, Predicate<T> p) {

        return invertirTRH(takeWhileTRHAux(list, acum, p).eval(), NIL);

        // return invertirTRH(takeTRHAux(list, acum, elem).eval(), NIL);
    }

    static <T> TailCall<ListaTRH<T>> removeTRHAux(ListaTRH<T> list, ListaTRH<T> acum, T elem) {

        if (list.isEmpty()) {

            return TailCall.ret(acum);
        } else {
            if (list.head().equals(elem)) {

                Supplier<TailCall<ListaTRH<T>>> ret = () ->
                        removeTRHAux(list.tailH(), acum, elem);
                return TailCall.sus(ret);
            } else {

                Supplier<TailCall<ListaTRH<T>>> ret1 = () ->
                        removeTRHAux(list.tailH(), preppendTRH(list.head(), acum), elem);
                return TailCall.sus(ret1);

            }
        }

    }

    static <T> ListaTRH<T> removeTRH(ListaTRH<T> list, T elem) {

        return invertirTRH(removeTRHAux(list, ListaTRH.of(), elem).eval(), NIL);

    }


    static <T> TailCall<ListaTRH<T>> concatenarTRHAux(ListaTRH<T> ListaTRH1, ListaTRH<T> ListaTRH2) {
        if (ListaTRH1.isEmpty()) {
            return TailCall.ret(ListaTRH2);
        } else {
            Supplier<TailCall<ListaTRH<T>>> ret1 = () ->
                    concatenarTRHAux(ListaTRH1.tailH(), preppendTRH(ListaTRH1.head(), ListaTRH2));

            return TailCall.sus(ret1);
        }
    }

    static <T> ListaTRH<T> concatenarTRH(ListaTRH<T> lista1, ListaTRH<T> lista2) {
        return concatenarTRHAux(invertirTRH(lista1, ListaTRH.of()), lista2).eval();
    }

    static <T> TailCall<ListaTRH<T>> replaceTRHAux(ListaTRH<T> list, T elem, T newElem, ListaTRH<T> acum) {

        if (list.isEmpty()) {
            return TailCall.ret(acum);

        } else if (list.head().equals(elem)) {
            Supplier<TailCall<ListaTRH<T>>> ret = () ->
                    replaceTRHAux(list.tailH(), elem, newElem, preppendTRH(newElem, acum));

            return TailCall.sus(ret);

        } else {

            Supplier<TailCall<ListaTRH<T>>> ret = () ->
                    replaceTRHAux(list.tailH(), elem, newElem, preppendTRH(list.head(), acum));
            return TailCall.sus(ret);
        }
    }

    public static <T> ListaTRH<T> replaceTRH(ListaTRH<T> list, T elem, T newElem) {
        return invertirTRH(replaceTRHAux(list, elem, newElem, ListaTRH.of()).eval(), NIL);

    }

    static <T> TailCall<Integer> sizeTRHAux(ListaTRH<T> list, Integer intit) {
        if (list.isEmpty()) {
            return TailCall.ret(intit);
        } else {

            Supplier<TailCall<Integer>> ret = () ->
                    sizeTRHAux(list.tailH(), intit + 1);

            return TailCall.sus(ret);
        }
    }

    static <T> Integer sizeTRH(ListaTRH<T> list) {

        return sizeTRHAux(list, 0).eval();
    }


    static <T> TailCall<Optional<T>> containsTRHAux(ListaTRH<T> original, ListaTRH<T> acum, T elem) {
        if (original.isEmpty()) {
            return TailCall.ret(Optional.empty());
        }
        if (original.head().equals(elem)) {
            return TailCall.ret(Optional.of(original.head()));
        } else {

            Supplier<TailCall<Optional<T>>> ret = () ->
                    containsTRHAux(original.tailH(), preppendTRH(original.head(), acum), elem);

            return TailCall.sus(ret);
        }
    }

    static <T> Optional<T> containsTRH(ListaTRH<T> original, T elem) {
        return (Optional<T>) containsTRHAux(original, NIL, elem).eval();
    }


    static <T> TailCall<T> forEachTRH(ListaTRH<T> lista, Consumer<T> fn) {
        return forEachTRHAux(lista, ListaTRH.NIL, fn);
    }

    static <T> TailCall<T> forEachTRHAux(ListaTRH<T> acc, ListaTRH<T> retorno, Consumer<T> fn) {
        if (acc.isEmpty()) {
            return TailCall.ret(appendTRH(acc.head(), retorno).head());//retorno.append(acc.head()).eval().head()
        } else {
            fn.accept(acc.head());
            Supplier<TailCall<T>> ret = () ->
                    forEachTRHAux(acc.tailH(), retorno, fn);

            return TailCall.sus(ret);
        }
    }

    public static void main(String[] args) {

        ListaTRH<Integer> original = ListaTRH.of(1, 2, 3, 4, 5);
        ListaTRH<Integer> original2 = ListaTRH.of(11, 12, 13, 14, 15);

        var l1 = appendTRH(6, original);
        System.out.println("Append= " + l1);

        var l2 = preppendTRH(0, original);
        System.out.println("Preppend= " + l2);


        var l3 = invertirTRH(original, NIL);
        System.out.println("Invertir= " + l3);

        var l = dropTRH(original, 3);
        System.out.println("Drop= " + l);

        var l4 = dropWhileTRH(original, x -> x % 2 == 0);
        System.out.println("DropWhile= " + l4);

        var l5 = takeTRH(original, NIL, 2);
        System.out.println("Take= " + l5);

        var l6 = takeWhileTRH(original, NIL, x -> x % 2 == 0);
        System.out.println("TakeWhile= " + l6);

        var l7 = removeTRH(original, 4);
        System.out.println("remove= " + l7);

        var l8 = concatenarTRH(original, original2);
        System.out.println("concat= " + l8);

        var l9 = replaceTRH(original, 3, 20);
        System.out.println("replace= " + l9);

        var l10 = sizeTRH(original);
        System.out.println("Size= " + l10);

        var l11 = containsTRH(original, 3);
        System.out.println("Contains= " + l11);

        var l12 = forEachTRH(original, x -> System.out.print(x)).eval();
        System.out.println(l12);

    }

}
