import lista.Lista;

import static lista.Lista.NIL;

public class MainSublistas {


    static Lista<Integer> subListSinMax(Lista<Integer> ls, Integer valor) {

        if (ls.isEmpty()) {

            return NIL;
        } else {

            if (ls.head() < valor) {

                return subListSinMax(ls.tail(), valor).preppend(ls.head());

            } else {


                return subListSinMax(ls.tail(), ls.head()).preppend(valor);
            }

        }

    }


    static Lista<Integer> subListSinMin(Lista<Integer> ls, Integer valor) {

        if (ls.isEmpty()) {

            return NIL;
        } else {

            if (ls.head() > valor) {

                return subListSinMin(ls.tail(), valor).preppend(ls.head());

            } else {


                return subListSinMin(ls.tail(), ls.head()).preppend(valor);
            }

        }

    }

    static Lista<Integer> burbuja(Lista<Integer> ls) {

        if(ls.isEmpty()){

            return NIL;
        }else {

            var min= Lista.min(ls);

            var listaSInMin = subListSinMin(ls.tail(),ls.head());


            return burbuja(listaSInMin).preppend(min);

        }


    }

    public static void main(String[] args) {

        var l1 = Lista.of(4, 6, 2, 3, 8);

        var r1 = subListSinMax(l1.tail(), l1.head());

        System.out.println(r1);

        var r2 = subListSinMin(l1.tail(), l1.head());
        System.out.println(r2);


        var r3 = burbuja(l1);

        System.out.println(r3);

    }


}
