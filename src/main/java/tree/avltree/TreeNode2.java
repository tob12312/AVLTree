package tree.avltree;

public class TreeNode2<E> {
    protected E element;
    protected TreeNode2<E> left;
    protected TreeNode2<E> right;
    protected int height = 0; // New data field

    public TreeNode2(E e){
        element = e;
    }

}


