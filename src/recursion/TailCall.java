package recursion;

import java.util.function.Supplier;

public sealed interface TailCall<T> permits Return,Suspend {

    T eval();

    TailCall<T> resume();

    boolean isSuspended();

    static <T> TailCall<T> ret (T t){
        return new Return<>(t);

    }

    static <T> TailCall<T> sus (Supplier<TailCall<T>> s){
        return new Suspend<>(s);

    }

}



