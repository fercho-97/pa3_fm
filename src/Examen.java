import lista.Lista;

import java.util.function.Function;
import java.util.function.Predicate;

record TuplaEx<T, U> (String t1, String t2) {
	public String toString() {
		return String.format("(%s,%s)", this.t1, this.t2);
	}
}

public class Examen {

	/*
	/*
	 * Representarlo mediante una lista de tuplas de dos elementos, donde cada tupla
	 * contiene la conexi�n entre dos nodos, por ejemplo (m,n), (m,p), (m,o), etc.
	 * Realizar un programa que permita encontrar todos los nodos �sucesores� de un
	 * nodo dado. Por ejemplo, los nodos sucesores de �m� son �n�, �p�, �o�.
	 * Realizar dos implementaciones: - Algoritmo tail-recursivo - Algoritmo
	 * utilizando operaciones de folding
	 */

	public static Lista<String> sucesores(Lista<TuplaEx<String, String>> ls, String letra, Lista<String> aux) {

		if (ls == null) {

			return aux;
		} else {
			if (ls.head().t1().equals(letra)) {
				if (aux == null) {
					aux = Lista.of(ls.head().t2());
					return sucesores(ls.tail(), letra, aux);
				} else {
					return sucesores(ls.tail(), letra, aux.append(ls.head().t2()));
				}
			} else {

				return sucesores(ls.tail(), letra, aux);
			}
		}
	}

	// - Algoritmo utilizando operaciones de folding

	/*
	public static Lista<String> sucesoresFolLeft(Lista<TuplaEx<String, String>> lt,
			Function<Lista<String>, Function<TuplaEx<String, String>, Lista<String>>> fn) {

		return lt.foldLeft(new Lista<String>(lt.head().t2()), fn).tail();
	}

	/*
	 * 
	 * 2. Dada un conjunto de monedas de determinadas denominaciones, realizar un
	 * programa que permita descomponer un valor dado en la suma de valores tomados
	 * del conjunto de monedas (cambiar un valor por monedas) A manera de ejemplo,
	 * considere el conjunto de monedas {5,2,1} descomponer el valor 19. El
	 * resultado es: {5,5,5,2,2}
	 * 
	 */
/*
	public static Lista<Integer> cambio(Integer valor, Lista<Integer> denominaciones, Lista<Integer> aux) {

		if (denominaciones == null) {

			return aux;
		} else {
			if (denominaciones.head() <= valor) {

				if (aux == null) {
					aux = new Lista<>(denominaciones.head());
					return cambio((valor - denominaciones.head()), denominaciones, aux);
				} else {

					return cambio((valor - denominaciones.head()), denominaciones, aux.append(denominaciones.head()));
				}

			} else {

				return cambio(valor, denominaciones.tail(), aux);

			}

		}

	}


	 * 3. Dada una lista que contenga cualquier tipo de dato, escribir un algoritmo
	 * que permita generar una sublista �eliminando� n-elementos al inicio de la
	 * lista tal que cumplan una determinado condici�n. Implementar el algoritmo
	 * utilizando un algoritmo tail-recursivo con recursi�n en el heap.
	 */
/*
	public static <T> Lista<T> filtroCondicion(Lista<T> ls, Predicate<T> c, Lista<T> aux) {

		if (ls == null) {

			return aux;

		} else {
			if (c.test(ls.head())) {
				
				return filtroCondicion(ls.tail(), c, aux.preppend2(ls.head()));


			}
			
			return filtroCondicion(ls.tail(), c, aux);
		}

	}

 */

/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Lista<TuplaEx<String, String>> listaTupla = new Lista<>(new TuplaEx<>("m", "n"), new TuplaEx<>("m", "p"),
				new TuplaEx<>("m", "o"), new TuplaEx<>("n", "q"), new TuplaEx<>("q", "s"), new TuplaEx<>("q", "r"),
				new TuplaEx<>("p", "q"), new TuplaEx<>("o", "r"));

		var r1 = sucesores(listaTupla, "m", null);

		System.out.println(r1);

		Function<Lista<String>, Function<TuplaEx<String, String>, Lista<String>>> fn = ls -> t -> {

			if (t.t1().compareTo("m") == 0) {
				return ls.append(t.t2());
			}
			return ls;
		};

		var r2 = sucesoresFolLeft(listaTupla, fn);

		System.out.println(r2);

		Lista<Integer> denominaciones = Lista.of(5, 3, 2, 1);

		Lista<Integer> cambio = cambio(38, denominaciones, null);
		System.out.println(cambio);

		var r3 = filtroCondicion(denominaciones, x->x%2==0, new Lista<>());
		
		System.out.println(r3);

	}
	*/


}
