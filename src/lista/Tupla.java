package lista;

public class Tupla<T,R> {

    private final T arg1;
    private final R arg2;


    public Tupla(T arg1, R arg2) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public T arg1() {
        return this.arg1;
    }

    public R arg2() {
        return this.arg2;

    }

    @Override
    public String toString(){

        return String.format("(%s, %s)", this.arg1, this.arg2);
    }

}
