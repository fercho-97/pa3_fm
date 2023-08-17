import java.util.function.Function;

public class FuncionesRecursivas {


    static int add(int x, int y) {

        if (y == 0) {
            return x;

        } else {
            return add(x + 1, y - 1);

        }
    }

    static Function<Integer, Function<Integer, Integer>> add =
            x -> y -> y == 0
                    ? x
                    : FuncionesRecursivas.add.apply(x + 1).apply(y - 1);

    public static void main(String[] args) {

        int ret1 = add(5, 3);
        System.out.println(ret1);
/*
        Function<Integer, Function<Integer,Integer>> add =
                x->y->{
                    if(y==0){
                        return x;

                    }else {
                       return add.apply(x+1).apply(y-1);
                    }
                };

 */
        var ret2 = FuncionesRecursivas.add.apply(5).apply(3);
        System.out.println(ret2);

    }
}
