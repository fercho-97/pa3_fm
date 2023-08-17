import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoizer<T,U> {

    private final Map<T,U> cache = new HashMap<>();

    private Memoizer(){

    }

    private Function<T,U> memoizeInternal(Function<T,U> fn){

        return x-> cache.computeIfAbsent(x, fn::apply);

    }
    public static<T,U>  Function<T,U> memoize(Function<T,U> fn){
        //return x->cache.computeIfAbsent(x, y->fn.apply(x));
        return new Memoizer<T,U>().memoizeInternal(fn);

//        return x->{
//          if (cache.containsKey(x)){
//
//              return cache.get(x);
//            }else{
//
//              U tmp = fn.apply(x);
//              cache.put(x, tmp);
//              return tmp;
//          }
//
//        };



    }

}
