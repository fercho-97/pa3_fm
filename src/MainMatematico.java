public class MainMatematico {

    public static Integer factorial(Integer n) {
        /*
         * if (n <= 1) {
         *
         * return 1;
         *
         * }
         *
         * return n * factorial(n - 1);
         */
        return n <= 1 ? 1 : n * factorial(n - 1);

    }

    public static Integer factorialRecursivoAux(Integer n, Integer acumular) {

        /*
         * if (n <= 1) {
         *
         * return acumular;
         *
         * }
         *
         * return factorialRecursivoAux(n-1, n*acumular );
         */
        return n <= 1 ? acumular : factorialRecursivoAux(n - 1, n * acumular);

    }

    public static Integer factorialRecursivo(Integer n) {
        return factorialRecursivoAux(n, 1);

    }

    public static Integer fibonacci(Integer n) {
        /*
         * if (n == 0) { return 0;
         *
         * } else if (n == 1) { return 1; } else { return fibonacci(n - 1) + fibonacci(n
         * - 2); }
         *
         */

        return n == 0 ? 0 : n == 1 ? 1 : fibonacci(n - 1) + fibonacci(n - 2);

    }

    public static Integer fibonacciAux(Integer n, Integer a, Integer b) {

        return n == 0 ? a : fibonacciAux(n - 1, b, a + b);
    }

    public static Integer fibonacciTailRecursivo(Integer n) {
        return fibonacciAux(n, 0, 1);
    }

    public static void main(String[] args) {


    }
}
