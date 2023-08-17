import lista.Lista;

public class MainDeber4 {
    public static void main(String[] args) {

        Lista<Integer> miLista = Lista.of(2, 3, 7, 4, 8);
        Lista<Integer> acum = Lista.NIL;
        var la = miLista.appendTRH(5);

        System.out.println(la);

//        var l1 = Lista.invertirTH(miLista,Lista.NIL);
//        System.out.println(l1.eval());

        var l2 = miLista.dropTH( 2);
        System.out.println(l2);

        var l3 = miLista.drop(2);
        System.out.println(l3);

        var l4 = Lista.dropWhileTHAux(miLista, x->x%2==0);
        System.out.println(l4.eval());


        var l5 = miLista.takeTH( 2, acum);
        System.out.println("take= "+l5);
        System.out.println(miLista.take(2));

    }
}
