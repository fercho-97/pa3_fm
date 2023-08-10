
import recursion.TailCall;

import java.util.function.Supplier;

public class TestRecursion {

    public static int suma1(int x, int y) {

        if (y == 0) {
            return x;

        } else {
            return suma1(x + 1, y - 1);
        }

    }

    public static TailCall<Integer> suma2(Integer x,Integer y){
        return y==0
                ?TailCall.ret(x)
                : TailCall.sus(()-> suma2(x+1, y-1));

        }

        public static void main(String[] args) {

//        Return<Integer> ret = new Return<>(5);
//        Suspend<Integer> n1 = new Suspend<>(ret);
//        Suspend<Integer> n2 = new Suspend<>(n1);
        // Suspend<Integer> ini = new Suspend<>(n2);

        var ini = suma2(5, 1000000);

//        TailCall<Integer> tmp = ini;
//        while (tmp.isSuspended()) {
//            tmp = tmp.resume();
//
//        }


        Integer sum = ini.eval();
        System.out.println(sum);

        // System.out.println(tmp);


    }
}
