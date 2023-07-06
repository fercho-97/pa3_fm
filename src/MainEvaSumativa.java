import lista.Lista;
import tree.BinTree;

import static lista.Lista.concatenar;

public class MainEvaSumativa {

    static <T> Lista<T> preOrder(BinTree<T> tree) {

        if (tree == BinTree.Leaf) {

            return Lista.NIL;
        } else {

            return Lista.of(tree.value(), preOrder(tree.left()), preOrder(tree.right()));

        }

    }

    static <T> Lista<T> inOrder(BinTree<T> tree) {

        if (tree == BinTree.Leaf) {

            return Lista.NIL;
        } else {

            var l = tree.left();
            var r = tree.right();

            return Lista.of(inOrder(l), tree.value(), inOrder(r));

        }

    }



    public static <T> Lista<T> postOrder(BinTree<T> tree){
        if(tree==BinTree.Leaf) {
            return Lista.NIL;
        }else {


            var lsLeft=postOrder(tree.left());

            var lsRight=postOrder(tree.right());


            var tmp=lsRight.append(tree.value());
            return concatenar(lsLeft, tmp);

        }

    }
    public static Double eval(BinTree<String> tree) {
        if(tree==BinTree.Leaf) {
            return 0.0;
        }else {

            var lsLeft=eval(tree.left());

            var lsRight=eval(tree.right());

            String op =tree.value();
            if (op.equals("+")) {
                return lsLeft+lsRight;
            }
            else if (op.equals("-")) {
                return lsLeft-lsRight;
            }
            else if (op.equals("*")) {
                return lsLeft*lsRight;
            }
            else if (op.equals("/")) {
                return lsLeft/lsRight;
            }
            return Double.parseDouble(tree.value());
        }


    }



    public static void main(String[] args) {
/*
        BinTree<Integer> n9 = BinTree.of(9);
        BinTree<Integer> n5 = BinTree.of(5, n9, BinTree.Leaf);
        BinTree<Integer> n2 = BinTree.of(2);
        BinTree<Integer> n1 = BinTree.of(1, n2, n5);

        System.out.println("Tamaño del arbol= " + n1.size());
        System.out.println("arbol= " + n1);

        var r1 = preOrder(n1);
        var r2 = inOrder(n1);
        var r3 = postOrder(n1);

        System.out.println("preOrder= " + r1);
        System.out.println("inOrder= " + r2);
        System.out.println("postOrder= " + r3);


 */



        var n3= BinTree.of("3");
        var n2= BinTree.of("2");
        var n5= BinTree.of("5");
        var n8= BinTree.of("8");
        var n4= BinTree.of("4");


        var nMult = BinTree.of("*",n3,n5);
        var nRes = BinTree.of("-", nMult, n2);
        var nDiv = BinTree.of("/",  n8,n4);
        var nSuma =BinTree.of("+",  nRes, nDiv);


        double resultado = eval(nSuma);

        var post5= postOrder(nSuma);

        System.out.println(nSuma);
        System.out.println(resultado);

    }


}

