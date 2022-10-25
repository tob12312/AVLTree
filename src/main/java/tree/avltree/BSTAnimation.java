package tree.avltree;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BSTAnimation extends Application {
    @Override // Override the start method in the Application class
public void start(Stage primaryStage) {
       //  BST<Integer> tree = new BST<>(); // Create a tree

        // Ny ref til AVLTree
        AVLTree<Integer> tree = new AVLTree<>();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);
        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                 tfKey, btInsert, btDelete);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
             int key = Integer.parseInt(tfKey.getText());
             if (tree.søk(key)) { // key is in the tree already
                 view.displayTree();
                 view.setStatus(key + " is already in the tree");
                 }
             else {
                 tree.leggInn(key); // Insert a new key  // FEILER
                 view.displayTree();
                 view.setStatus(key + " is inserted in the tree");
                 }
             });
        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
             if (!tree.søk(key)) { // key is not in the tree
                 view.displayTree();
                 view.setStatus(key + " is not in the tree");
                 }
             else {
                 tree.slett(key); // Delete a key
                 view.displayTree();
                 view.setStatus(key + " is deleted from the tree");
                 }
             });

         // Create a scene and place the pane in the stage
         Scene scene = new Scene(pane, 450, 250);
         primaryStage.setTitle("BSTAnimation"); // Set the stage title
         primaryStage.setScene(scene); // Place the scene in the stage
         primaryStage.show(); // Display the stage

        testAVLTree();
         }

         public void testAVLTree() {
            AVLTree<Integer> tree2 = new AVLTree<Integer>(new Integer[] {25, 20, 5});
            System.out.println("After inseting 25, 20, 5:");
            printTree(tree2);

            tree2.leggInn(34);
            tree2.leggInn(50);
            System.out.println("\nAfter inserting 34, 50:");
            printTree(tree2);

             tree2.leggInn(30);
             System.out.println("\nAfter inserting 30:");
             printTree(tree2);

             tree2.leggInn(10);
             System.out.println("\nAfter inserting 10:");
             printTree(tree2);

             tree2.slett(30);
             tree2.slett(34);
             tree2.slett(50);
             System.out.println("\nAfter removing 34, 30, 50:");
             printTree(tree2);

             tree2.slett(5);
             System.out.println("\nAfter removing 5:");

             System.out.println("\nTraverse the elements in the tree: ");
             for (int e: tree2) {
                 System.out.println(e + " ");
             }
         }

         public static void printTree(BST tree2) {
            System.out.println("\nInorder (sorted): ");
            tree2.inorder();

             System.out.println("\nInPostorder: ");
             tree2.postOrder();

             System.out.println("\nInPreorder: ");
             tree2.preOrder();

             System.out.println("\nThe number of nodes is " + tree2.getSize());
             System.out.println();

         }

    }
