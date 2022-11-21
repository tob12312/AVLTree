package tree.avltree;

import java.util.Collection;
import java.util.Collections;

public interface Tree<E> extends Collection<E> {
    boolean søk(E e);

    boolean leggInn(E e);

    boolean slett(E e);

    int getSize();

    default void inorder(){} // 5 20 25

    default void postOrder(){} // 5 25 20

    default void preOrder(){} // 20 5 25
    @Override
    default boolean isEmpty(){
        return size() ==0;
    }
    @Override
    default boolean contains(Object e){return søk((E)e);}
    @Override
    default boolean add(E e){
        return leggInn(e);
    }
    @Override
    default boolean remove(Object e){
        return slett((E)e);
    }
    @Override
    default int size(){
        return getSize();
    }


    // DENNE FUNGERER NÅ!
    @Override
    default boolean containsAll(Collection<?> c){
        for (Object o: c)
            if (!søk((E) o)) return false;
        return true;
    }

    // DENNE FUNGERER NÅ!
    @Override
    default boolean addAll(Collection<? extends E> c){
        for (E e: c)
            if (!leggInn(e)) return false;
        return true;
    }

    // DENNE FUNGERER NÅ!
    @Override
    default boolean removeAll(Collection<?> c){
        for (Object o : c)
            if (!slett((E) o)) return false;
        return true;
    }

    // DENNE FUNGERER NÅ!
    @Override
    default boolean retainAll(Collection<?> c){
        for (E e : this) { // looper this
            if (!c.contains(e)) // hvis e ikke ligger i this:
                slett(e);
        }
        return true; // hva bør kontrolleres/ returneres?
    }

    // DISSE ER FREMDELES IKKE IMPLEMENTERT!
    @Override
    default Object[] toArray(){
        return null;
    }

    @Override
    default <T> T[] toArray(T[] array){
        return null;
    }

}


