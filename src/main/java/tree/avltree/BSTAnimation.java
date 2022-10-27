package tree.avltree;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class BSTAnimation extends Application {
    BorderPane pane = new BorderPane(); // rotpanel
    Boolean erInt = true; // int som default = true

    AVLTree<Integer> tree = new AVLTree<>(); // Create a AVLTree for Integer
    AVLTree<String> tree2 = new AVLTree<>(); // Create a AVLTree for String

    BTView view = new BTView(tree); // Create a View for Integer
    BTView view2 = new BTView(tree2); // Create a View for String

    TextField tfKey;

    /**
     * Start
     *
     * @param primaryStage
     */
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        pane.setCenter(view); // integer-view = default : for rett plassering
        tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        Button btTest   = new Button("Test");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(
                new Label("Select / Reset: "),
                toggleDatatype(),
                new Label("Enter a key: "),
                tfKey,
                btInsert,
                btSearch,
                btDelete,
                btTest);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        // Events
        btInsert.setOnAction(e -> {
            if (erInt) insertInteger();
            else insertString();
        });
        btSearch.setOnAction(e -> {
            if (erInt) searchInteger();
            else searchString();
        });
        btDelete.setOnAction(e -> {
            if (erInt) deleteInteger();
            else deleteString();
        });
        btTest.setOnAction(e -> {
            if (erInt) testInteger(); // test 10 random Integer
            else testString(); // test 10 random String
            testAVLTree(); // test fra boka, skriver til konsoll
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }





    /**
     * Metode for å insert når Integer-verdier
     */
    private void insertInteger() {
        view.setStatus("");
        int key;
        try {
            key = Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException e) {
            view.displayTree();
            view.setStatus("String is illegal, only integers allowed");
            tfKey.clear();
            return;
        }
        if (tree.søk(key)) { // key is in the tree already
            view.displayTree();
            view.setStatus(key + " is already in the tree, new insert failed");
        } else {
            tree.leggInn(key); // Insert a new key
            view.displayTree();
            view.setStatus(key + " is inserted in the tree");
        }
    }




    /**
     * Metode for insert når String verdier
     */
    private void insertString() {
        String key = tfKey.getText();
        if (isNumeric(key)) { // kontrollerer for tekst/ tall
            view2.setStatus(key + " is illegal, letters/ String only");
            tfKey.clear(); // tømmer tekstfelt
        }
        else if (tree2.søk(key)) { // key is in the tree already
            view2.displayTree();
            view2.setStatus(key + " is already in the tree, new insert failed");
        } else {
            tree2.leggInn(key); // Insert a new key
            view2.displayTree();
            view2.setStatus(key + " is inserted in the tree");
        }
    }




    /**
     * Metode for å søke etter Integer-verdier
     */
    private void searchInteger() {
        int key;
        try {
            key = Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException e) {
            view.displayTree();
            view.setStatus("String is illegal, only integers allowed");
            tfKey.clear();
            return;
        }
        if (tree.søk(key)) { // key is in the tree OK
            view.displayTree();
            view.setStatus(key + " identified by color");
        } else {
            view.setStatus("");
            view.displayTree();
            view.setStatus(key + " is not found");
        }
    }




    /**
     * Metode for å søke etter String-verdier
     */
    private void searchString() {
        String key = tfKey.getText();
        if (isNumeric(key)) {
            view2.setStatus(key + " is illegal, letters/ String only");
            tfKey.clear(); // tømmer tekstfelt
        }
        else if (tree2.søk(key)) { // key is in the tree OK
            view2.displayTree();
            view2.setStatus(key + " identified by color");
        } else {
            view2.setStatus("");
            view2.displayTree();
            view2.setStatus(key + " is not found");
        }
    }




    /**
     * Metode for å slette Integer-verdier
     */
    private void deleteInteger() {
        int key;
        try {
            key = Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException e) {
            view.displayTree();
            view.setStatus("String is illegal, only integers allowed");
            tfKey.clear();
            return;
        }
        if (!tree.søk(key)) { // key is not in the tree
            view.displayTree();
            view.setStatus(key + " is not in the tree");
        } else {
            tree.slett(key); // Delete a key
            view.displayTree();
            view.setStatus(key + " is deleted from the tree");
        }
    }




    /**
     * Metode for å slette String-verdier
     */
    private void deleteString() {
        String key = tfKey.getText();
        if (isNumeric(key)) {
            view2.setStatus(key + " is illegal, letters/ String only");
            tfKey.clear(); // tømmer tekstfelt
        }
        else if (!tree2.søk(key)) { // key is not in the tree
            view2.displayTree();
            view2.setStatus(key + " is not in the tree");
        } else {
            tree2.slett(key); // Delete a key
            view2.displayTree();
            view2.setStatus(key + " is deleted from the tree");
        }
    }




    /**
     * Metoden returnerer toggle for valg av dataype
     * Bytter mellom to ulike visninger, med hver sine data
     * Rydder visning og data for hver veksling
     *
     * @return
     */
    private HBox toggleDatatype() {
        RadioButton tb1 = new RadioButton("Integer");
        RadioButton tb2 = new RadioButton("String ");
        ToggleGroup gruppe = new ToggleGroup();
        tb1.setToggleGroup(gruppe);
        tb2.setToggleGroup(gruppe);
        gruppe.selectToggle(erInt ? tb1 : tb2);
        tb1.setOnAction(e -> {
            tree2.clear(); // nullstill instans tree2
            view.displayTree(); // vis tømt panel
            erInt = true; // skal nå håndtere int
            pane.setCenter(view); // viser insans tilpasset int
            view.setStatus(""); // nullstiller status
            view2.setStatus(""); // nullstiller status
        });
        tb2.setOnAction(e -> { // tilsvarende for String
            tree.clear();
            view2.displayTree();
            erInt = false;
            pane.setCenter(view2);
            view.setStatus("");
            view2.setStatus("");
        });
        HBox ramme = new HBox();
        ramme.setPadding(new Insets(8, 0, 8, 0));
        ramme.setSpacing(5);
        ramme.getChildren().addAll(tb1, tb2);
        ramme.setAlignment(Pos.CENTER);
        return ramme;
    }




    /**
     * Metode for å teste Integer-tre. Setter inn 10 random Integers
     */
    private void testInteger() {
        for (int i=0; i<10; i++) {
            int key = new Random().nextInt(100);
            tree.leggInn(key); // Insert a new key
            view.displayTree();
            view.setStatus("Test Integer executed");
        }
    }



    /**
     * Metode for å teste Integer-tre. Setter inn 10 random Integers
     */
    private void testString() {
        for (int i=0; i<10; i++) {
            String key = generateRandomString();
            tree2.leggInn(key); // Insert a new key
            view2.displayTree();
            view2.setStatus("Test String executed");
        }
    }


    /**
     * Hjelpemetode for å generere random strenger.
     *
     * @return String-verdi for insert i AVLTree<String>
     */
    public String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int antallTegn = 3;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(antallTegn)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }




    /**
     * Hjelpemtode for å avgjøre om en verdi er numerisk eller ikke
     * Tillater ikke numeriske verdier i AVLTree for String
     *
     * @param verdi for test
     * @return true = String
     */
    public static boolean isNumeric(String verdi) {
        if (verdi == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(verdi);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }





    /**
     * Metode for å teste at AVLTree fungerer som tiltenkt (hentet fra boka)
     */
     public void testAVLTree() {
        AVLTree<Integer> tree2 = new AVLTree<Integer>(new Integer[] {25, 20, 5});
        System.out.print("After inseting 25, 20, 5:");
        printTree(tree2);

        tree2.leggInn(34);
        tree2.leggInn(50);
        System.out.print("\nAfter inserting 34, 50:");
        printTree(tree2);

         tree2.leggInn(30);
         System.out.print("\nAfter inserting 30:");
         printTree(tree2);

         tree2.leggInn(10);
         System.out.print("\nAfter inserting 10:");
         printTree(tree2);

         tree2.slett(30);
         tree2.slett(34);
         tree2.slett(50);
         System.out.print("\nAfter removing 34, 30, 50:");
         printTree(tree2);

         tree2.slett(5);
         System.out.print("\nAfter removing 5:");

         System.out.print("\nTraverse the elements in the tree: ");
         for (int e: tree2) {
             System.out.println(e + " ");
         }
     }




    /**
     * Hjelpemetode for å skrive testresultater til konsoll
     */
     public static void printTree(BST tree2) {
        System.out.print("\nInorder (sorted): ");
        tree2.inorder();

         System.out.print("\nInPostorder: ");
         tree2.postOrder();

         System.out.print("\nInPreorder: ");
         tree2.preOrder();

         System.out.print("\nThe number of nodes is " + tree2.getSize());
         System.out.println();

     }

}
