package tree.avltree;
import java.util.Collection;
public interface Tree<E> extends Collection<E> {
    public boolean søk(E e);


    public boolean leggInn(E e);

    public boolean slett(E e);

    public int getSize();

    public default void inorder(){}

    public default void postOrder(){}

    public default void preOrder(){}
    @Override
    public default boolean isEmpty(){
        return size() ==0;
    }
    @Override
    public default boolean contains(Object e){
        return søk((E)e);
    }
    @Override
    public default boolean add(E e){
        return leggInn(e);
    }
    @Override
    public default boolean remove(Object e){
        return slett((E)e);
    }
    @Override
    public default int size(){
        return getSize();
    }
    @Override
    public default boolean containsAll(Collection<?> c){
        return false;
    }
    @Override
    public default boolean addAll(Collection<? extends E> c){
        return false;
    }
    @Override
    public default boolean removeAll(Collection<?> c){
        return false;
    }
    @Override
    public default boolean retainAll(Collection<?> c){
        return false;
    }
    @Override
    public default Object[] toArray(){
        return null;
    }
    @Override
    public default <T> T[] toArray(T[] array){
        return null;
    }

}


