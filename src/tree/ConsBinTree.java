package tree;

final class ConsBinTree<T> implements BinTree<T> {

    private final T value;

    private  final BinTree<T>left;

    private final BinTree<T>right;

    public ConsBinTree(T value, BinTree<T> left, BinTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public ConsBinTree(T value) {
        this.value = value;
        this.left = BinTree.Leaf;
        this.right = BinTree.Leaf;
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public BinTree<T> left() {
        return this.left;
    }

    @Override
    public BinTree<T> right() {
        return this.right;
    }
    public void print(int level){

        String tab = "-".repeat(level * 2);
        System.out.print(tab);
        System.out.printf("BinThree( %s\n", this.value);
        left.print(level + 1);
        right.print(level + 1);



/*
        if (left instanceof ConsBinTree<T> tmp)
            tmp.toStringAux(level+1);
        else left.toString();

        if (right instanceof ConsBinTree<T> tmp)
            tmp.toStringAux(level+1);
        else right.toString();


 */
    }

    @Override
    public String toString(){
        return String.format("BinTree(%s,%s,%s)", value.toString(), left.toString(), right.toString());

    }

    @Override
    public Integer size(){

        return 1+ left().size()+ right().size();
    }
}
