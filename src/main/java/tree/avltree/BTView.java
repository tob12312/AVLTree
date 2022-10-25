package tree.avltree;
import javafx.scene.layout.Pane;
 import javafx.scene.paint.Color;
 import javafx.scene.shape.Circle;
 import javafx.scene.shape.Line;
 import javafx.scene.text.Text;
public class BTView extends Pane {
    private AVLTree<Integer> tree = new AVLTree<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; //
    BTView(AVLTree<Integer> tree) {
         this.tree = tree;
         setStatus("Tree is empty");
         }

         public void setStatus(String msg) {
         getChildren().add(new Text(20, 20, msg));
         }

         public void displayTree() {
         this.getChildren().clear(); // Clear the pane
         if (tree.getRoot() != null) {

             displayTree(tree.getRoot(), getWidth() / 2, vGap,
                     getWidth() / 4);
             }
         }

         /** Display a subtree rooted at position (x, y) */
         private void displayTree(AVLTree.TreeNode<Integer> root,
         double x, double y, double hGap) {

         if (root.left != null) {
             // Draw a line to the left node
             getChildren().add(new Line(x - hGap, y + vGap, x, y));
             // Draw the left subtree recursively
             displayTree(root.left, x - hGap, y + vGap, hGap / 2);
             }

         if (root.right != null) {
             // Draw a line to the right node
             getChildren().add(new Line(x + hGap, y + vGap, x, y));
             // Draw the right subtree recursively
             displayTree(root.right, x + hGap, y + vGap, hGap / 2);
             }

         // Display a node
         Circle circle = new Circle(x, y, radius);
         circle.setFill(Color.WHITE);
         circle.setStroke(Color.BLACK);
         getChildren().addAll(circle,
                 new Text(x - 4, y + 4, root.element + ""));
         }
 }

