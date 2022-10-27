package tree.avltree;


/**
 * Class BST inneholder logikk for beregning av binærtre
 * Indre klasse TreeNode ivaretar verdi, pekere og noders høyde i treet
 * Indre klasse InorderIterator implementerer Iterator, intern iterator
 *
 * @param <E>
 */
public class BST <E extends Comparable<E>> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    /**
     * Konstruktør for ??
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /**
     *
     * @param objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }



    /**
     * Metode for søk. Benyttes også av AVLTree
     * @param e
     * @return
     */
    @Override
    public boolean søk(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0)
                current = current.left; // TODO : endret til left
            else if (c.compare(e, current.element) > 0)
                current = current.right;
            else return true;
        }
        return false;
    }


    /**
     * Metode for insert av node i tre.
     * @param e
     * @return
     */
    @Override
    public boolean leggInn(E e) {
        if (root == null)
            root = createNewNode(e); // TODO: endret til metode for instansiering
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
                } else return false;
            } // TODO: Lukket while-løkka her
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
     *
     * @param root
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element + " ");
        inorder(root.right);
    }



    /**
     *
     */
    @Override
    public void postOrder() {
        postOrder(root);
    }


    /**
     *
     * @param root
     */
    protected void postOrder(TreeNode<E> root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.element + " ");
    }


    /**
     *
     */
    @Override
    public void preOrder() {
        preOrder(root);
    }


    /**
     *
     * @param root
     */
    protected void preOrder(TreeNode<E> root) {
        if (root == null) return;
        System.out.println(root.element + " ");
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * Indre klasse for tre-node. Utvidet med høyde for bruk i AVLTree
     * TODO: Legge inn ny variabel : KEY? INDEKS?
     * @param <E>
     */
    public static class TreeNode<E> {
        protected int height = 0;
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }


    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     * @return
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




    @Override
    /** Delete an element from the binary tree.
     Return true if the element is deleted successfully
     Return false if the element is not in the tree */
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
     *
     * @return
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
        private java.util.ArrayList<E> list =
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
            list.add(root.element);
            inorder(root.right);
        }

        /** More elements for traversing? */
        @Override
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        /** Get the current element and move to the next */
        @Override
        public E next() {
            return list.get(current++);
        }

        /** Remove the current element */
        @Override
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            slett(list.get(--current));
            list.clear(); // Clear the list
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
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<E>(e);
    }

} // slutt class BST


