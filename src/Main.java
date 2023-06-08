import lista.Cons;
import lista.Lista;

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
        Lista<Integer> miLista = Lista.of(2,3,7,4,8,2);
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

        if(lEmpty== Lista.NIL){

            System.out.println("vacia");
        }
        System.out.println("ToString");

        System.out.println(miLista);


        var l3= miLista.append(87);
        System.out.println(l3);
        //System.out.println(miLista.toString1());

        var l4= miLista.preppend(23);

        System.out.println(l4);

        var l5 = l4.remove(2);
        System.out.println(l5);


    }
}