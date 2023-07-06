package tree;

import lista.Lista;

import java.util.List;

public sealed interface BinTree<T> permits ConsBinTree, LeafBinTree {

   BinTree Leaf = new LeafBinTree();

   T value();
   BinTree<T> left();
   BinTree<T> right();

   static<T> BinTree<T> of(T value){

      return new ConsBinTree<T>(value);

   }

   static<T> BinTree<T> of(T value, BinTree<T> left, BinTree<T> right){

      return new ConsBinTree<T>(value, left, right);

   }

  Integer size();


   static<T> BinTree<T> buildTree(Lista<T> ls){

      if (ls.isEmpty()){

         return Leaf;

      } else {

         T h = ls.head();
         Lista<T> t = ls.tail();

         int k = ls.tail().size()/2;

         var leftList= t.take(k);

         var rightList= t.drop(k);

         return  BinTree.of(h, buildTree(leftList), buildTree(rightList));

      }


   }

   void print(int level);
}
