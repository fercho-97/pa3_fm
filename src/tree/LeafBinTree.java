package tree;

final class LeafBinTree<T> implements BinTree<T> {

    @Override
    public T value() {
        return null;
    }

    @Override
    public BinTree<T> left() {
        return null;
    }

    @Override
    public BinTree<T> right() {
        return null;
    }

    @Override
    public Integer size() {
        return 0;
    }

    @Override
    public String toString(){
        return "Leaf";

    }

    public void print(int level){

        String tab = "-".repeat(level * 2);
        System.out.print(tab);
        System.out.println("LEAF");
    }

}
