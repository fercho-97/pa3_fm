import lista.Cons;
import lista.Lista;

import java.util.function.Function;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    /*
        Cons<Integer> n8 = new Cons<>(8, Lista.NIL);
        Cons<Integer> n4 = new Cons<>(4, n8 );
        Cons<Integer> n7 = new Cons<>(7, n4);
        Cons<Integer> n3 = new Cons<>(3, n7);
*/

        /*
        Lista<Integer> n8 = Lista.of(8, Lista.NIL);
        Lista<Integer> n4 = Lista.of(4, n8);
        Lista<Integer> n7 = Lista.of(7, n4);
        Lista<Integer> n3 = Lista.of(3, n7);

        Lista<Integer> miLista = new Cons<>(2, n3);
*/
        Lista<Integer> miLista = Lista.of(2, 3, 7, 4, 8, 2);
        Lista<Integer> l2 = Lista.of(2);
        Lista<?> lEmpty = Lista.of();


        System.out.println(miLista.head());
        System.out.println();

        var tmp = miLista;
        while (tmp != Lista.NIL) {
            System.out.println(tmp.head());
            tmp = tmp.tail();
        }

        var tmp2 = l2;
        while (tmp2 != Lista.NIL) {
            System.out.println(tmp2.head());
            tmp2 = tmp2.tail();
        }

        System.out.println(lEmpty);

        if (lEmpty == Lista.NIL) {

            System.out.println("vacia");
        }
        System.out.println("ToString");

        System.out.println(miLista);


        var l3 = miLista.append(87);
        System.out.println(l3);
        //System.out.println(miLista.toString1());

        var l4 = miLista.preppend(23);

        System.out.println(l4);

        var l5 = l4.remove(2);
        System.out.println(l5);

        var l6 = l4.drop(2);
        System.out.println("control= " + l4);
        System.out.println("drop 2= " + l6);

        var l7 = l4.take(2);
        System.out.println("take 2= " + l7);


        var l8 = l4.dropWhile(x->x%2==0);
        System.out.println("DropWhile x%2==0= " + l8);

        var l9 = l4.takeWhile(x->x>7);
        System.out.println("takeWhile x>8= " + l9);

        var l10 = Lista.max(l4);
        System.out.println("maximo de control= " + l10);

        var l11 = Lista.suma(l4);
        System.out.println("suma de elems de control= " + l11);


        Function<Integer, Integer> f1 = x -> 3*x;

        Function<Integer, Integer> f2 =x -> x*x;

        Function<Function<Integer, Integer>, Function<Function<Integer, Integer>,Function<Integer, Integer>>> comp = f->g->x-> g.apply(f.apply(x));

        Function<Integer, Integer> fg = comp.apply(f1).apply(f2);
        Function<Integer, Integer> gf = comp.apply(f2).apply(f1);

        Function<Function<Integer, Integer>,Function<Integer, Integer>> t = comp.apply(f1);

        Integer r = fg.apply(2);
        Integer r2 = gf.apply(2);


        System.out.println(r);
        System.out.println(r2);

        Function<Integer, Integer> cm = Lista.comp(f1,f2);

        var r3 = cm.apply(2);

        System.out.println(r3);
    }
}