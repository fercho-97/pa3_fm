package listaDeber;

record Cons<T>(T head, ListaTR<T> tail) implements ListaTR<T>, ListaTRH<T> {
    @Override
    public String toString() {
        return String.format("[%s,%s]",head,tail.toString());
    }

    @Override
    public T head() {
        // TODO Auto-generated method stub
        return head;
    }

    @Override
    public ListaTRH<T> tailH() {
        return (ListaTRH<T>) tail;
    }

    @Override
    public ListaTR<T> tail() {
        // TODO Auto-generated method stub
        return tail;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }
}
