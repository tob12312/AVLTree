package tree.avltree;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    boolean søk(E e);

    boolean leggInn(E e);

    boolean slett(E e);

    int getSize();

    default void inorder(){}

    default void postOrder(){}

    default void preOrder(){}
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
    @Override
    default boolean containsAll(Collection<?> c){
        return false;
    }
    @Override
    default boolean addAll(Collection<? extends E> c){
        return false;
    }
    @Override
    default boolean removeAll(Collection<?> c){
        return false;
    }
    @Override
    default boolean retainAll(Collection<?> c){
        return false;
    }
    @Override
    default Object[] toArray(){
        return null;
    }
    @Override
    default <T> T[] toArray(T[] array){
        return null;
    }

}


