import lista.Lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static lista.Lista.NIL;
import static lista.Lista.reduce;

public class OperacionesListas {


    static <U, T> List<U> map(List<T> ls, Function<T, U> fn) {
        List<U> ret = new ArrayList<>();
        for (T t : ls) {
            ret.add(fn.apply(t));

        }

        return ret;
    }

    static Integer fold1(List<Integer> ls, Integer identidad, Function<Integer, Function<Integer, Integer>> fn) {

        /*
        {1,2,3,4}
        fold=(((0+1)+2)+3)+4

         */
        Integer acum = identidad;
        int index = 1;
        for (Integer elem : ls) {
            System.out.printf("it%d: %d + %d\n", index++, acum, elem);
            acum = fn.apply(acum).apply(elem);
        }

        return acum;
    }

    static String fold2(List<Integer> ls, String identidad, Function<String, Function<Integer, String>> fn) {

        /*

            f(v1+1)=""+1="1"
            f("1"+2)= ""+2="12"


        */

        String acum = identidad;
        // int index = 1;
        for (Integer elem : ls) {
            // System.out.printf("it%d: %d + %d\n", index++, acum, elem);
            acum = fn.apply(acum).apply(elem);
        }
        return acum;
    }

    static <T, U> U foldLeft(List<T> ls, U identidad, Function<U, Function<T, U>> fn) {

        U acum = identidad;

        for (T elem : ls) {

            acum = fn.apply(acum).apply(elem);
        }
        return acum;
    }

    static <T, U> U foldRight(List<T> ls, U identity, Function<T, Function<U, U>> fn) {
        U acum = identity;

        for (int i = ls.size() - 1; i >= 0; i--) {

            acum = fn.apply(ls.get(i)).apply(acum);
        }

        return acum;
    }

    public static void testReduction() {

        List<Integer> ls = List.of(1, 2, 3, 4, 5);

// Function<Integer,Double>

        var ret = map(ls, x -> x * 1.2);

        System.out.println(ret);
//-------------------
        var lista = Lista.of(1, 2, 3, 4, 5);

        var ret2 = lista.map(x -> x * 1.2);
        System.out.println(ret2);

//------------
        var ret3 = lista.mapIt(x -> x * 1.2);
        System.out.println(ret3.inv());
//System.out.println(ret3.invertir());

        Integer sum = reduce(ls, x -> y -> x + y);
        System.out.println(sum);

    }


    public static void main(String[] args) {

        testReduction();
        List<Integer> ls = List.of(1, 2, 3, 4);
        Integer suma = fold1(ls, 0, x -> y -> x + y);
        System.out.println("La suma es = " + suma);

        String concat = fold2(ls, "", str -> i -> str.concat(i.toString()));
        System.out.println("El resultado es= " + concat);


        var suma2 = foldLeft(ls, 0, x -> y -> x + y);
        System.out.println("La suma2 es = " + suma2);

        String concat2 = foldLeft(ls, "", str -> i -> str + i);
        System.out.println("El resultado2 es= " + concat2);

        var ret = foldLeft(ls, "0", str -> x -> String.format("(%s + %d)", str, x));

        System.out.println("ret es= " + ret);
        List<String> id = new ArrayList<>();
        Function<List<String>, Function<Integer, List<String>>> fn = list -> x -> {
            String tmp = "s" + x.toString();
            list.add(tmp);
            return list;
        };


        List<String> p = foldLeft(ls, new ArrayList<String>(), fn);
        System.out.println("El resultado de p= " + p);

        List<Integer> values = List.of(1, 2, 3, 5, 8, 2, 5, 1, 2, 15, 1, 32, 1);
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer t : values) {
            if (counts.containsKey(t)) {
                var cc = counts.get(t);
                counts.put(t, cc + 1);
            } else {
                counts.put(t, 1);
            }
        }
        System.out.println(counts);
        //UXT ->U
        Function<Map<Integer, Integer>, Function<Integer, Map<Integer, Integer>>> fn2 =
                map -> t -> {
                    if (map.containsKey(t)) {
                        var cc = counts.get(t);
                        map.put(t, cc + 1);
                    } else {
                        map.put(t, 1);
                    }
                    return map;
                };
        Map<Integer, Integer> valores = foldLeft(values, new HashMap<>(), map -> t -> {
            if (map.containsKey(t)) {
                var cc = counts.get(t);
                map.put(t, cc + 1);
            } else {
                map.put(t, 1);
            }
            return map;
        });
        System.out.println(valores);

        Map<Integer, Integer> vi = new HashMap<>();
        var counts2 = foldLeft(values, vi, fn2);
        System.out.println(counts2);


        Function<Integer, Function<String, String>> fn1 = x -> str -> String.format("(%s + %s)", x, str);

        Integer concat4 = foldRight(ls, 0, x -> y -> x + y);

        String concat3 = foldRight(ls, "0", fn1);
        System.out.println("El de foldRight concat es= " + concat3);
        System.out.println("El de foldRight suma es= " + concat4);

        var ls1 = Lista.of(1, 2, 3, 4);

        var se = ls1.reduce2(0, x -> y -> x + y);
        System.out.println("El reduce de la lista= " + se);


        var str2 = ls1.foldRight("0", x -> s -> String.format("(%d + %s)", x, s));

        System.out.println("El foldright de la lista= " + str2);


        var str3 = ls1.foldLeft("0", s -> x -> String.format("(%s + %d)", s, x));

        System.out.println("El foldright de la lista= " + str3);

        Function<Lista<Integer>,Function<Integer,Lista<Integer>>> fn3= list->x->{

            var l1 =  list.preppend(x);

            return l1;
        };


        var inv = ls1.foldLeft(Lista.NIL, list-> x->list.preppend(x));
        var inv2 = ls1.foldLeft(Lista.NIL, fn3);

        System.out.println("El foldright de la lista (invertir)= " + inv);
        System.out.println("El foldright de la lista (invertir)= " + inv2);

        // Function<Integer, Function<Lista<Integer>,Integer>> fnSize= x->list->x+1;


        var size = ls1.foldLeft(0, x->list->x+1);


        System.out.println("El foldright de la lista (invertir)= " + size);

        System.out.println(System.getProperty("java.version"));

        var map = ls1.inv().foldLeft(Lista.NIL, list->x->list.preppend(x.doubleValue()));
        var map2 = ls1.foldRight(Lista.NIL, x->list->list.preppend(x.doubleValue()));

        System.out.println("lista original= " + ls1);
        System.out.println("El foldleft de la lista (map)= " + map);
        System.out.println("El foldRight de la lista (map)= " + map2);


    }

}