package listaDeber;

//--nodo final
record Nil<T>() implements ListaTR<T>, ListaTRH<T> {


    @Override
    public T head() {
        return null;
    }

    @Override
    public ListaTRH<T> tailH() {
        return null;
    }

    @Override
    public ListaTR<T> tail() {
        return null;
    }

    public boolean isEmpty(){
            return true;
    }

    @Override
    public String toString() {
        return "NIL";
    }
}