package recursion;

import java.util.function.Supplier;

record Suspend<T>(Supplier<TailCall<T>> res) implements TailCall<T>{



    @Override
    public T eval() {
        // TODO Auto-generated method stub

        TailCall<T> tmp =this;
        while (tmp.isSuspended()) {
            tmp= tmp.resume();
        }
        return tmp.eval();
    }

    @Override
    public TailCall<T> resume() {
        // TODO Auto-generated method stub
        return res.get();
    }

    @Override
    public boolean isSuspended() {
        // TODO Auto-generated method stub
        return true;
    }


}