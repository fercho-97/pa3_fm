import recursion.TailCall;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.function.Supplier;

public class TestRecursion {

    static Integer factorialRecursivoAux(Integer n, Integer acumular) {


//        if (n <= 1) {
//            return acumular;
//        }
//
//        return factorialRecursivoAux(n - 1, n * acumular);

        return n <= 1 ? acumular : factorialRecursivoAux(n - 1, n * acumular);

    }

    static Integer factorialRecursivo(Integer n) {
        return factorialRecursivoAux(n, 1);

    }

    static Integer fibonacci(Integer n) {

//        if (n == 0) {
//            return 0;
//
//        } else if (n == 1) {
//            return 1;
//        } else {
//            return fibonacci(n - 1) + fibonacci(n - 2);
//

        return n == 0 ? 0 : n == 1 ? 1 : fibonacci(n - 1) + fibonacci(n - 2);

    }

    static BigInteger fibonacciRec(BigInteger n, BigInteger a, BigInteger b) {

        return n == BigInteger.ONE ? b : fibonacciRec(n.subtract(BigInteger.ONE), b, a.add(b));
    }


    static TailCall<BigInteger>  fibonacciTailAux(BigInteger acc1, BigInteger acc2, BigInteger n) {

        if (n.equals(BigInteger.ZERO)) {
            return TailCall.ret(BigInteger.ZERO);

        } else if (n.equals(BigInteger.ONE)) {
            return TailCall.ret(acc1.add(acc2));
        } else {

            Supplier<TailCall<BigInteger>> ret = ()->fibonacciTailAux(acc2, acc1.add(acc2), n.subtract(BigInteger.ONE));
            return  TailCall.sus(ret);

        }

    }

    static BigInteger fibonacciTail(int n) {
        return fibonacciTailAux(BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(n)).eval();
    }


    public static int suma1(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return suma1(x + 1, y - 1);
        }

    }

    public static TailCall<Integer> suma2(Integer x, Integer y) {
        return y == 0
                ? TailCall.ret(x)
                : TailCall.sus(() -> suma2(x + 1, y - 1));

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

        // var fibo = TestRecursion.fibonacci(20);

//            System.out.println(fibo);

//            for (int a=0; a<=1000;a++){
//                System.out.printf("%4d: %d\n",a, fibonacciRec(a,0,1));
//
//            }

        var fibo = fibonacciTail(500);

        System.out.println(fibo);


    }
}