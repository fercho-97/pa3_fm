import lista.Lista;
import tree.BinTree;

public class Trabajo01Main {
    public static  void main (String[] args){

        Lista<Integer> ls = Lista.of(1,2,3,4,5,6,7,8);

        BinTree<Integer> n2 = BinTree.of(2);
        BinTree<Integer> n5 = BinTree.of(5);
        BinTree<Integer> n1 = BinTree.of(1, n2,n5);

        System.out.println(n1.size());
        System.out.println(n1);

        BinTree<Integer> r1 = BinTree.buildTree(ls);

        System.out.println(r1);

        r1.print(1);



    }
}
