import java.util.function.Function;

public class FuncionesMemoria {

    public static void main(String[] args) {

        Function<Integer, Integer> doubleValue = x -> {
            FuncionesRecursivas2.pause();
            return x * 2;
        };

        //Memoizer<Integer,Integer> memo = new Memoizer<>();

        //debe ir con función anonima (doubleValue)
        var doubleValueCache = Memoizer.memoize(doubleValue);

        System.out.println(doubleValueCache.apply(3));
        System.out.println(doubleValueCache.apply(3));
        System.out.println(doubleValueCache.apply(3));
        System.out.println(doubleValueCache.apply(3));
        System.out.println(doubleValueCache.apply(3));


    }
}
