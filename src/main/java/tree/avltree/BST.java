package tree.avltree;


/**
 * Class BST inneholder logikk for beregning av binærtre.
 * Indre klasse TreeNode ivaretar verdi, pekere, height og size.
 * Indre klasse InorderIterator implementerer Iterator, intern iterator.
 *
 * @param <E>
 */
public class BST <E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;



    /**
     * Default konstruktør
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }



    /**
     * Konstruktør som tar inn spesifisert comparator
     */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }




    /**
     * Konstruktør tar inn array av elementer
     *
     * @param objects array av elementer for insert i BST
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (E object : objects) {
            add(object);
        }
    }



    /**
     * Metode for søk på element/ key
     *
     * @param e element/ key
     * @return bekreftelse - true/ false
     */
    @Override
    public boolean søk(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0)
                current = current.left; // ENDRET FRA RIGHT TIL LEFT
            else if (c.compare(e, current.element) > 0)
                current = current.right;
            else return true;
        }
        return false;
    }



    /**
     * Metode for insert av node/ element i tre.
     *
     * @param e element/ key
     * @return bekreftelse - true/ false
     */
    @Override
    public boolean leggInn(E e) {
        if (root == null)
            root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;  // current overtar root
            while (current != null) {
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else return false; // duplikat
            } // ENDRET: LUKKET WHILE HER
            if (c.compare(e, parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // insert vellykket
    }


    /**
     *
     */
    @Override
    public void inorder() {
        inorder(root);
    }



    /**
     * Metoden viser BST sortert inorder
     *
     * @param root node av class TreeNode
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }



    /**
     * Parameterløs drivermetode
     *
     */
    @Override
    public void postOrder() {
        postOrder(root);
    }


    /**
     * Metoden viser BST sortert postOrder
     *
     * @param root node av class TreeNode
     */
    protected void postOrder(TreeNode<E> root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.element + " ");
    }


    /**
     * Parameterløs drivermetode
     *
     */
    @Override
    public void preOrder() {
        preOrder(root);
    }


    /**
     * Metoden viser BST sortert preOrder
     *
     * @param root node av class TreeNode
     */
    protected void preOrder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * Indre klasse for tre-node. Utvidet med høyde (og size) for bruk i AVLTree
     *
     * @param <E>
     */
    public static class TreeNode<E> {
        protected int height = 0;
        protected int size = 0; // antall barn/ noder i subtre av this
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }



    /**
     * Metoden returnerer size
     *
     * @return size
     */
    @Override
    public int getSize() {
        return size;
    }



    /**
     * Returns the root of the tree
     *
     * @return TreeNode
     */
    public TreeNode<E> getRoot() {
        return root;
    }




    /**
     * Returns a path from the root leading to the specified element
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }
        return list; // Return an array list of nodes
    }


    /**
     * Metoden sletter forekomster i BST.
     * Returnerer true hvis vellykket.
     * Returnerer false hvis ikke funnet.
     *
     * @param e element/ key
     * @return vellykket/ ikke funnet
     */
    @Override
    public boolean slett(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break; // Element is in the tree pointed at by current
        }
        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
        // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
        // Case 2: The current node has a left child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }
        size--;
        return true; // Element deleted successfully
    }


    /**
     * Metoden implementerer egen iterator ved override.
     *
     * @return instans av egen iterator
     */
    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }



    /**
     * Indre klasse som implementerer Iterator
     * For iterering av BST/ AVLTree
     * Benytter reglene for binærtre for å navigere
     */
    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private final java.util.ArrayList<E> LIST =
                new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list




    /**
     *
     */
    public InorderIterator() {
        inorder(); // Traverse and store
    }


    /**
     *
     */
    private void inorder() {
        inorder(root);
    }



    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
        if (root == null)return;
        inorder(root.left);
        LIST.add(root.element);
        inorder(root.right);
    }



    /** More elements for traversing? */
    @Override
    public boolean hasNext() {
        return current < LIST.size();
    }



    /** Get the current element and move to the next */
    @Override
    public E next() {
        return LIST.get(current++);
    }



    /** Remove the current element */
    @Override
    public void remove() {
        if (current == 0) // next() has not been called yet
            throw new IllegalStateException();
        slett(LIST.get(--current));
        LIST.clear(); // Clear the list
        inorder(); // Rebuild the list
    }
}



    /**
     * Metode for å nullstille treet
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }



    /**
     * Metode for å returnerer TreeNode instans
     *
     * @param e elementverdi/ key til node
     * @return ny instans av TreeNode
     */
    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

} // slutt class BST


