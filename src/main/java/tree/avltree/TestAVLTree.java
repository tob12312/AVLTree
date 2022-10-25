package tree.avltree;

public class TestAVLTree {
    public static void main(String[] args) {
         // Create an AVL tree
         AVLTree<Integer> tree = new AVLTree<Integer>(new Integer[]{25, 5, 20});
         System.out.print("After inserting 25, 20, 5:");
         printTree(tree);

         tree.leggInn(34);
         tree.leggInn(50);
         System.out.print("After inserting 34, 50:");
         printTree(tree);

         tree.leggInn(30);
         System.out.print("\nAfter inserting 30");
         printTree(tree);

         tree.leggInn(10);
         System.out.print("\nAfter inserting 10");
         printTree(tree);

         tree.slett(34);
         tree.slett(30);
         tree.slett(50);
         System.out.print("\nAfter removing 34, 30, 50:");
         printTree(tree);

         tree.slett(5);
         System.out.print("\nAfter removing 5:");
         printTree(tree);

         System.out.print("\nTraverse the elements in the tree: ");
         for (int e: tree) {
             System.out.print(e + " ");
             }
         }

         public static void printTree(BST tree) {
         // Traverse tree
         System.out.print("\nInorder (sorted): ");
         tree.inorder();
         System.out.print("\nPostorder: ");
         tree.postOrder();
         System.out.print("\nPreorder: ");
         tree.preOrder();
         System.out.print("\nThe number of nodes is " + tree.getSize());
        System.out.println();
         }
}
