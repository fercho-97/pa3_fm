package lista;

import java.util.List;

public sealed interface Lista<T> permits Cons, Nil {
    Lista NIL = new Nil();

    T head();

    Lista<T> tail();

    boolean isEmpty();

    // public static final Lista NIL = new Nil();

    static <T> Lista<T> of(T h, Lista<T> t) {
        return new Cons<>(h, t);
    }

    static <T> Lista<T> of(T... elems) {

        Lista<T> ret = Lista.NIL;
        for (int i = elems.length - 1; i >= 0; i--) {
            T h = elems[i];
            var tmp = Lista.of(h, ret);
            ret = tmp;

        }
        return ret;
    }
/*
   default String toString1(){
        StringBuilder sb = new StringBuilder();
        var tmp = this;
        while (!tmp.isEmpty()){

            sb.append(tmp.head());
            sb.append(", ");
            tmp = tmp.tail();
        }
        sb.append("NIL");
         return sb.toString();
   }

 */

    default Lista<T> append(T val) {

        if (this == NIL) {
            return Lista.of(val);
        } else {

            return Lista.of(head(), tail().append(val));
        }

    }

    default Lista<T> preppend(T val) {

        if (this == NIL) {
            return Lista.of(val);
        } else {
            return Lista.of(val, this);
        }

    }

    default Lista<T> remove(T val) {
        var tmp = this;
        var ret = NIL;
        while (tmp != NIL) {

            if (!(tmp.head().equals(val))) {
                ret = ret.append(tmp.head());

            }
            tmp = tmp.tail();
          
        }

        return ret;

    }
}

