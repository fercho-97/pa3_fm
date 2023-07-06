import lista.Lista;


import java.util.Map;
import java.util.function.Function;

import static lista.Lista.NIL;

public class binariosMain {

    record Tupla<T,U>(T _t1, U _t2){
        public String toString() {
            return String.format("(%s,%s)",this._t1,this._t2);
        }
    }

    static Lista<Integer> sumarBinario(Integer valor, Lista<Integer> ls) {

        if (ls.isEmpty()) {

            return NIL;
        } else if (valor == 0) {

            return ls.invertir();
        } else {

            Integer h = ls.head();
            Lista<Integer> t = ls.tail();

            var r = 1 - h;

            return sumarBinario(h, t.invertir()).append(r);

        }

    }


    static Lista<Integer> sumarDosBinarios(Lista<Integer> l1, Lista<Integer> l2, Integer llevar) {

        if (l1.isEmpty() && l2.isEmpty()) {

            return NIL;
        } else if (l1.isEmpty()) {

            return sumarBinario(llevar, l2);

        } else if (l2.isEmpty()) {

            return sumarBinario(llevar, l1);
        } else {

            Integer h = l1.head();
            Lista<Integer> t = l1.tail();

            Integer h2 = l2.head();
            Lista<Integer> t2 = l2.tail();

            Integer suma = h + h2 + llevar;
            Integer llev = suma / 2;
            Integer head = suma % 2;

            Lista<Integer> tmp = sumarDosBinarios(t, t2, llev);
            return tmp.preppend(head);


        }


    }


    public static <T,U,V> Lista<V> zip(Lista<T> ls1, Lista<U> ls2, Function<T, Function<U, V>> fn){
        //return null;

        if(ls1.tail().isEmpty() && ls2.tail().isEmpty()) {
            V elem=fn.apply(ls1.head()).apply(ls2.head());
            return Lista.of(elem);
        }else {
            var elem = fn.apply(ls1.head()).apply(ls2.head());
            return zip(ls1.tail(), ls2.tail(), fn).preppend(elem);
        }
    }



    public static void main(String[] args) {

        Lista<Integer> l1 = Lista.of(1, 0, 1, 1);
        Lista<Integer> l2 = Lista.of(1, 1, 1, 1, 0);


        var r1 = sumarBinario(0, l1);
        var r2 = sumarBinario(1, l1);

        var r3 = sumarDosBinarios(l1, l2, 0);


        System.out.println(r1);
        System.out.println(r2);

        System.out.println(r3);

        var n1=Lista.of(3,2,3,7,5);
        var n2=Lista.of(6,8,4,8,7);
        //var res3=zip(n1,n2,t->u->t+U);
        String[] nombres= {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};
        Lista<Tupla<String,String>> res3=zip(n1,n2,t->u->new Tupla<String, String>(nombres[t],nombres[u]));
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(res3);
        System.out.println("-----------------------");


    }

}
