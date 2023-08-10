package recursion;

record Return<T>(T t) implements TailCall<T>{


    @Override
    public T eval() {
        // TODO Auto-generated method stub
        return t;
    }

    @Override
    public TailCall<T> resume() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSuspended() {
        // TODO Auto-generated method stub
        return false;
    }
}