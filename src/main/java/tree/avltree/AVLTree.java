package tree.avltree;

/**
 * Class AVLTree inneholder logikk for konstruksjon av AVL-tre
 * Arver mye logikk og egenskaper fra super BST
 * Egen logikk for balansering
 * Overrider metode for sletting pga rebalansering
 * Benytter class TreNode for innleggelse av verdier, høyde tilført som parameter
 *
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>> extends BST<E> {

    /**
     * Default konstruktør, indirekte kall på super
     */
    public AVLTree() {
    }


    /**
     * Konstruktør med spesifisert komparator
     * @param c spesifisert komparator
     */
    public AVLTree(java.util.Comparator<E> c) {
        super(c);
    }

    /**
     * Konstruktør for array av elementer
     *
     * @param objects array m elementer for lagring i AVLTree
     */
    public AVLTree(E[] objects) {
        super(objects);
    }



    @Override
    /** Insert an element and rebalance if necessary */
    public boolean leggInn(E e) {
        boolean successful = super.leggInn(e); // leggInn i BST utført
        if (!successful)
            return false; // e is already in the tree
        else
            balancePath(e); // Balance from e to the root if necessary
        return true; // e is inserted
    }




    /**
     * Update the height of a specified node
     */
    private void updateHeightAndSize(TreeNode<E> node) {
        if (node.left == null && node.right == null) {// node is a leaf {
            node.height = 0;
            node.size = 1; // lagt til size med oppdatering her
        } else if (node.left == null) {// node has no left subtree
            node.height = 1 + node.right.height;
            node.size = 1 + node.right.size;
        } else if (node.right == null) {// node has no right subtree
            node.height = 1 + node.left.height; // ENDRET FRA MINUS TIL PLUSS
            node.size = 1 + node.left.size;
        }
        else {
            node.height = 1 + Math.max(node.right.height, node.left.height);
            node.size = 1 + node.left.size + node.right.size;
        }
    }


    /**
     * Drivermetode for enklere kall
     * Oppgave 26.5 - usikker på om det er dette boka legger opp til, men tror det.
     * I 26.5 skal man redigere metoder insert(leggInn) og delete(slett) for å oppdatere size,
     * men rebalansering må vel potensielt utføres først(?). Vi mener det er en god løsning og heller
     * redigere metode updatateHeight, da denne kjører til rett tid og med rett logikk.
     * Metoden updateHeigtAndSize kunne ligget i class TreeNode, og kunne vært implementert som to separate metoder.
     *
     * @param k nodes relative order in Tree
     * @return kall på metode med komplett parameterliste
     */
    public E find(int k) {
        if (k < 1 || k > this.size) {
            return null;
        }
        return find(k, getRoot());
    }


    /**
     * Metoden finner the kTh smallest element(value)
     * Egen drivermetode med færre parameter
     *
     * @param k nodes order
     * @param root root of tree
     * @return kTh smallest element
     */
    private E find(int k, TreeNode<E> root) {
        if (root.left == null && k == 1)
            return root.element;
        else if (root.left == null && k == 2)
            return root.right.element;
        else if (k <= root.left.size)
            return find(k, root.left);
        else if (k == root.left.size + 1)
            return root.element;
        else if (k > root.left.size + 1)
            return find(k - root.left.size - 1, root.right);
        else return null;
    }




    /**
     * Metode for å balansere nodene fra e til root
     * @param e element in TreeNode
     */
    private void balancePath(E e) {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            TreeNode<E> A = path.get(i);
            updateHeightAndSize(A); // juster høyde og size på noder
            TreeNode<E> parentOfA = (A == root) ? null : path.get(i - 1);
            // balansefaktor avgjør behov for rotasjoner
            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor(A.left) <= 0)
                        balanceLL(A, parentOfA); // LL rotation
                    else
                        balanceLR(A, parentOfA); // LR rotation
                    break;
                case +2:
                    if (balanceFactor(A.right) >= 0)
                        balanceRR(A, parentOfA); // RR rotation
                    else
                        balanceRL(A, parentOfA); // RL rotation
            }
        }
    }




    /**
     * Metode for å beregne balansefaktor
     *
     * @param node node av class TreeNode
     * @return balansefaktor
     */
    private int balanceFactor(TreeNode<E> node) {
        if (node.right == null) // node has no right subtree
            return -node.height;
        else if (node.left == null) // node has no left subtree
            return +node.height;
        else
            return node.right.height - node.left.height;
    }




    /**
     * Metode for LL rotasjon, både node A og B er venstre-tunge
     * @param A node av class TreeNode
     * @param parentOfA node av class TreeNode
     */
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left; // A is left-heavy and B is left−heavy
        if (A == root)
            root = B;
        else {
            if (parentOfA.left == A)
                parentOfA.left = B;
            else
                parentOfA.right = B;
        }
        A.left = B.right; // Make T2 the left subtree of A
        B.right = A; // Make A the left child of B

        // juster høyde og size på noder
        updateHeightAndSize(A);
        updateHeightAndSize(B);
    }




    /**
     * Metode for LR rotasjon, A er venstre-tung, B er høyde-tung
     * @param A node av class TreeNode
     * @param parentOfA node av class TreeNode
     */
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left; // A is left−heavy
        TreeNode<E> C = B.right; // B is right−heavy
        if (A == root)
            root = C;
        else {
            if (parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }
        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
        C.left = B;
        C.right = A;

        // juster høyde og size på noder
        updateHeightAndSize(A);
        updateHeightAndSize(B);
        updateHeightAndSize(C);
    }




    /**
     * Metode for RR rotasjon, både node A og B er høyre-tunge
     * @param A node av class TreeNode
     * @param parentOfA node av class TreeNode
     */
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy
        if (A == root)
         root = B;
        else {
            if (parentOfA.left == A)
                parentOfA.left = B;
        else
            parentOfA.right = B;
        }
        A.right = B.left; // Make T2 the right subtree of A
        B.left = A;

        // juster høyde og size på noder
        updateHeightAndSize(A);
        updateHeightAndSize(B);
    }




    /**
    * Metode for RL rotasjon, A er høyre-tung, B er venstre-tung
    * @param A node av class TreeNode
    * @param parentOfA node av class TreeNode
    */
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy
        if (A == root)
            root = C;
        else {
            if (parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }
        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        // juster høyde og size på noder
        updateHeightAndSize(A);
        updateHeightAndSize(B);
        updateHeightAndSize(C);
    }





    /** Delete an element from the AVL tree.
     185 * Return true if the element is deleted successfully
     186 * Return false if the element is not in the tree */
    @Override
    public boolean slett(E element) {
        if (root == null)
         return false; // Element is not in the tree

        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(element, current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (c.compare(element, current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else break; // Element is in the tree pointed by current
        }
        if (current == null) return false; // Element is not in the tree
        else slettCase1(element, current, parent); // Case 1: current has no left children
        size--;
        return true; // Element inserted
    }





    /**
     * Hjelpemetode for slett der current IKKE HAR har ventre-barn
     * Case 1: current has no left children
     *
     * @param element nodes value
     * @param current node av class TreeNode
     * @param parent node av class TreeNode
     */
    private void slettCase1(E element, TreeNode<E> current, TreeNode<E> parent) {
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null)
                root = current.right;
            else {
                if (c.compare(element, parent.element) < 0)  // ENDRET CURRENT.ELEMENT TIL PARENT.ELEMENT
                    parent.left = current.right;
                else
                    parent.right = current.right;
                // Balance the tree if necessary
                balancePath(parent.element);
            }
        }
        else slettCase2(current); // Case 2: The current node has a left child
    }





    /**
     * Hjelpemetode for slett der current HAR et ventre-barn
     * Case 2: The current node has a left child
     * Special case: parentOfRightMost is current
     *
     * @param current node av class TreeNode
     */
    private void slettCase2(TreeNode<E> current) {
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null) {
            parentOfRightMost = rightMost;
            rightMost = rightMost.right; // Keep going to the right
        }
        current.element = rightMost.element;
        // Eliminate rightmost node
        if (parentOfRightMost.right == rightMost)
            parentOfRightMost.right = rightMost.left;
        else
            // Special case: parentOfRightMost is current
            parentOfRightMost.left = rightMost.left;
        // Balance the tree if necessary
        balancePath(parentOfRightMost.element);
    }

 } // slutt AVLTree

