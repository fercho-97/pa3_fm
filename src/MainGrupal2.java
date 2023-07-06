import lista.Lista;

import java.util.function.Function;

import static lista.Lista.NIL;

public class MainGrupal2 {

    record Tupla<T,U>(T _t1, U _t2){
        public String toString() {
            return String.format("(%s,%s)",this._t1,this._t2);
        }
    }


    // recursivo
    static Lista<Integer> progresionRecursiva(Integer valor, Integer n, Function<Integer, Integer> fn) {

        if (n == 1) {

            return Lista.of(valor);
        }

        return progresionRecursiva(fn.apply(valor), n - 1, fn).preppend(valor);

    }

   /*
   public static <T> Tupla<Lista<T>, Lista<T>> dosSublistas(Lista<T> ls, Integer indice,
			Tupla<Lista<T>, Lista<T>> aux) {

		if (ls == null) {

			return aux;

		} else {
			if (indice > -1) {

				if (aux == null) {
					aux = new Tupla<>(new Lista<>(ls.head()), null);

					return dosSublistas(ls.tail(), indice - 1, aux);
				} else {

					aux = new Tupla<>(aux.t1().append(ls.head()), null);

					return dosSublistas(ls.tail(), indice - 1, aux);
				}

			} else {
				if (aux.t2() == null) {

					aux = new Tupla<>(aux.t1(), new Lista<>(ls.head()));

					return dosSublistas(ls.tail(), indice - 1, aux);
				} else {

					aux = new Tupla<>(aux.t1(), aux.t2().append(ls.head()));

					return dosSublistas(ls.tail(), indice - 1, aux);
				}

			}

		}

	}

    */

    public static void main(String[] args) {


        var r1 = progresionRecursiva(1, 5, x->x+1);

        System.out.println(r1);


    }


}
