package tree.avltree;

public class AVLTree<E extends Comparable<E>> extends BST<E> {

    public AVLTree() {}

    public AVLTree(E[] objects) {
         super(objects);
    }
  //  @Override /** Override createNewNode to create an AVLTreeNode */
  //   protected TreeNode<E> createNewNode(E e) {
 //        return new TreeNode<E>(e);
    //     }


         @Override /** Insert an element and rebalance if necessary */
 public boolean leggInn(E e) {

         boolean successful = super.leggInn(e);
         if (!successful) {
             System.out.println("DUPLIKAT"); // TODO: Duplikat oppdages bare i root. Root oppdateres ikke
             // TODO: referanse til root må oppdaters
             return false; // e is already in the tree
         }
         else {
             balancePath(e); // Balance from e to the root if necessary // FEILER
    //      System.out.println("Prøver å balansere treet");
             }
  //           System.out.println("ny node instans satt inn?"); DENNE KJØRER

         return true; // e is inserted

         }

         /** Update the height of a specified node */
         private void updateHeight(TreeNode<E> node) {
             if (node.left == null && node.right == null) {// node is a leaf
                 node.height = 0;
    //             System.out.println("node is a leaf");
             }
             else if (node.left == null) {// node has no left subtree
                 node.height = 1 + node.right.height;
  //               System.out.println("node has no left subtree");
             }
             else if (node.right == null) {// node has no right subtree
                 node.height = 1 - node.left.height;
     //            System.out.println("node has no right subtree");
             }
             else {
                 node.height = 1 +
                         Math.max(node.right.height, node.left.height);
                 System.out.println(
                         "Current høyde " + node.height +"\n"+
                                 "RIGHT høyde " + node.right.height +"\n"+
                                 "LEFT høyde " + node.left.height   +"\n"

                         );
             }
         }

    private void balancePath(E e) {
         java.util.ArrayList<TreeNode<E>> path = path(e);
         for (int i = path.size() - 1; i >= 0; i--) {
             TreeNode<E> A = (TreeNode<E>)(path.get(i));   // FEILER
             updateHeight(A);
             TreeNode<E> parentOfA = (A == root) ? null :
                     (TreeNode<E>)(path.get(i - 1));
             switch (balanceFactor(A)) {
                 case -2:
                     if (balanceFactor(A.left) <= 0) {
                         balanceLL(A, parentOfA); // Perform LL rotation
                         }
                     else {
                     balanceLR(A, parentOfA); // Perform LR rotation
                     }
                     System.out.println("CASE -2");
                 break;
                 case +2:
                      if (balanceFactor(A.right) >= 0) {
                      balanceRR(A, parentOfA); // Perform RR rotation
                      }
                     else {
                      balanceRL(A, parentOfA); // Perform RL rotation
                      }
                     System.out.println("CASE +2");
                  }
             }
         }
    private int balanceFactor(TreeNode<E> node) {
   //          System.out.println("balanceFactor kjører");
         if (node.right == null) {// node has no right subtree
             System.out.println("node has no RIGHT subtree " + -node.height);
             return -node.height;
         }
         else if (node.left == null) {// node has no left subtree
             System.out.println("node has no LEFT subtree " + node.height);
             return +node.height;
         }
         else {
             System.out.println("HØYRE minu VENSTRE " + node.right.height + " " + node.left.height);
             return node.right.height - node.left.height;
         }
     }


         /** Balance LL (see Figure 26.3) */
         private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
         TreeNode<E> B = A.left; // A is left-heavy and B is left−heavy

         if (A == root) {
             root = B;
             }
         else {
             if (parentOfA.left == A) {
                 parentOfA.left = B;
                 }
             else {
                 parentOfA.right = B;
                 }
             }

         A.left = B.right; // Make T2 the left subtree of A
         B.right = A; // Make A the left child of B
             System.out.println("HØYDE A gammel: " + A.height);
             System.out.println("HØYDE B gammel: " + B.height);

         updateHeight(A);
         System.out.println("HØYDE A ny: " + A.height);
         updateHeight(B);
             System.out.println("HØYDE B ny: " + A.height);
         }
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
         TreeNode<E> B = A.left; // A is left−heavy
         TreeNode<E> C = B.right; // B is right−heavy

         if (A == root) {
             root = C;
             }
         else {
             if (parentOfA.left == A) {
                 parentOfA.left = C;
             } else {
                 parentOfA.right = C;
             }
         }
        A.left = C.right; // Make T3 the left subtree of A
         B.right = C.left; // Make T2 the right subtree of B
         C.left = B;
         C.right = A;

         // Adjust heights
         updateHeight((TreeNode<E>)A);
         updateHeight((TreeNode<E>)B);
         updateHeight((TreeNode<E>)C);
         }
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
         TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

         if (A == root) {
             root = B;
             }
         else {
             if (parentOfA.left == A) {
                 parentOfA.left = B;
                 }
             else {
                 parentOfA.right = B;
                 }
            }
         A.right = B.left; // Make T2 the right subtree of A
         B.left = A;
         updateHeight((TreeNode<E>)A);
         updateHeight((TreeNode<E>)B);
         }

         /** Balance RL (see Figure 26.6) */
         private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
         TreeNode<E> B = A.right; // A is right-heavy
         TreeNode<E> C = B.left; // B is left-heavy

         if (A == root) {
             root = C;
             }
         else {
             if (parentOfA.left == A) {
                 parentOfA.left = C;
                 }
             else {
                 parentOfA.right = C;
                 }
             }
         A.right = C.left; // Make T2 the right subtree of A
         B.left = C.right; // Make T3 the left subtree of B
         C.left = A;
         C.right = B;
             updateHeight((TreeNode<E>)A);
              updateHeight((TreeNode<E>)B);
              updateHeight((TreeNode<E>)C);
              }
    @Override /** Delete an element from the AVL tree.
     185 * Return true if the element is deleted successfully
     186 * Return false if the element is not in the tree */
 public boolean slett(E element) {
     System.out.println("DET ER SLETT OVERRIDE SOM KJØRER");
         if (root == null)
             return false; // Element is not in the tree

         // Locate the node to be deleted and also locate its parent node
         TreeNode<E> parent = null;
         TreeNode<E> current = root;
         while (current != null) {
         //    if (element.compareTo(current.element) < 0) {
             if (c.compare(element, current.element) < 0) {
                 parent = current;
                 current = current.left;
                 }
             else if (c.compare(element, current.element) > 0) {
            //     else if (element.compareTo(current.element) > 0) {
                 parent = current;
                 current = current.right;
                 }
             else
             break; // Element is in the tree pointed by current
             }

         if (current == null)
             return false; // Element is not in the tree

         // Case 1: current has no left children (see Figure 25.10)
         if (current.left == null) {
             // Connect the parent with the right child of the current node
             if (parent == null) {
                 root = current.right;
                 }
             else {
                 if (element.compareTo(parent.element) < 0)
                     parent.left = current.right;
                 else
                 parent.right = current.right;

                 // Balance the tree if necessary
                 balancePath(parent.element);
                 }
        }

 else

    {
         // Case 2: The current node has a left child
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

         size--;
 return true; // Element inserted
 }

  //       /** AVLTreeNode is TreeNode plus height */
//         protected static class AVLTreeNode<E> extends BST.TreeNode<E> {
 //protected int height = 0; // New data field

 //        public AVLTreeNode(E e) {
 //        super(e);

 //        }
 //}

 }

