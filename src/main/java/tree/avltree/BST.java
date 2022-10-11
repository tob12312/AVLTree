package tree.avltree;

import java.util.Comparator;
import java.util.Iterator;

public class BST <E> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size=0;
    protected java.util.Comparator<E> c;
    public BST(){
        this.c= (e1, e2) ->((Comparable<E>)e1).compareTo(e2);
    }
    public BST(java.util.Comparator<E>c){
        this.c=c;
    }
    public BST(E[] objects){
        this.c= (e1, e2) ->((Comparable<E>)e1).compareTo(e2);
        for(int i=0; i< objects.length; i++){
            add(objects[i]);
        }
    }
    @Override
    public boolean søk(E e) {
    TreeNode<E> current = root;

    while(current!= null){
        if(c.compare(e, current.element)<0){
            current=current.right;
        }
        else if(c.compare(e, current.element) >0){
            current=current.right;
        }
        else return true;
    }
    return false;
    }


    @Override
    public boolean leggInn(E e) {
    if(root==null){
        root= new TreeNode(e);
    }
    else {
        TreeNode<E> parent=null;
        TreeNode<E> current=root;
        while(current!=null){
            if(c.compare(e, current.element)<0){
                parent=current;
                current=current.left;
            }
            else if(c.compare(e, current.element)>0){
                parent=current;
                current=current.right;
            }
            else return false;

        }

    }
    }

    @Override
    public boolean slett(E e) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }
}
