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

/**
 * Class BSTAnimation inneholder kontrollere for AVLTree/ BTView
 * Tegner et enkelt kontrollpanel og kjører applikasjonen fra Start()
 * Hver datatype som skal insertes benytter egne referanser og metoder
 *
 */
public class BSTAnimation extends Application {

    private BorderPane pane;
    private Boolean typeInt;
    private AVLTree<Integer> intTre;
    private AVLTree<String> stringTre;
    private BTView view;
    private BTView view2;
    private TextField tfKey;
    private Button btInsert, btDelete, btSearch, btFind, btTest;
    private RadioButton tb1, tb2;



    /**
     * Start
     *
     * @param primaryStage
     */
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        initVars();
        kontrollpanel();
        buttonEvents();
        toggleEvents();
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(new Scene(pane, 1200, 600)); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }




    /**
     * Metode for å initiere variabler/ referanser
     */
    private void initVars() {
        typeInt = true; // int som default = true

        // Referanser til AVLTree for hver type
        intTre = new AVLTree<Integer>(); // Create a AVLTree for Integer
        stringTre = new AVLTree<String>(); // Create a AVLTree for String

        // Referanser til BTView for hver type
        view = new BTView(intTre); // Create a View for Integer
        view2 = new BTView(stringTre); // Create a View for String
    }




    /**
     * Metode for å bygge opp kontrollpanel
     */
    private void kontrollpanel() {
        pane = new BorderPane(); // rotpanel
        pane.setCenter(view); // integer-view = default
        tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        btInsert = new Button("Insert ");
        btDelete = new Button("Delete ");
        btSearch = new Button("Search ");
        btFind =   new Button("Find   ");
        btTest =   new Button("Test   ");
        tb1 = new RadioButton("Integer");
        tb2 = new RadioButton("String ");
        HBox hBox = new HBox(5);
        hBox.setStyle("-fx-background-color: silver;");
        hBox.getChildren().addAll(
                new Label("Select / Reset: "),
                toggleDatatype(),
                new Label("Enter a key: "),
                tfKey,
                btInsert,
                btSearch,
                btDelete,
                btFind,
                btTest);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
    }





    /**
    * Eventhåndtering kontrollpanel Buttons
    * Metodekall avgjøres av datatype (Integer/ String)
    *
    */
    private void buttonEvents() {
        btInsert.setOnAction(e -> { // insert
            if (typeInt && erInteger()) insertInteger();
            else if (erString()) insertString();
        });
        btSearch.setOnAction(e -> { // search by element/ value
            if (typeInt && erInteger()) searchInteger();
            else if (erString()) searchString();
        });
        btDelete.setOnAction(e -> { // delete
            if (typeInt && erInteger()) deleteInteger();
            else if (erString()) deleteString();
        });
        // TODO : nye metoder
        btFind.setOnAction(e -> { // find kThe smallest element/ value
            if (typeInt && erInteger()) findInteger(); // let i Int-tre
            else if (!typeInt && erInteger()) findString(); // let i String-tre
            else { // bruker har tastet ikke-integer
                view2.displayTree();
                view2.setStatus("Illegal Key, Integers only");
            }
        });
        btTest.setOnAction(e -> { // test
            if (typeInt) testInteger(); // test +=10 random Integer
            else testString(); // test +=10 random String
            testAVLTree(); // test mønster, skriver til konsoll
        });
    }





    /**
     * Eventhåndtering kontrollpanel Toggle
     * Metodekall avgjøres av datatype (Integer/ String)
     *
     */
    private void toggleEvents() {
        tb1.setOnAction(e -> { // Ved toggling til Integer :
            byttTreType(); // hjelpemetode tømmer trær og tekstfelt
            view.displayTree(); // vis tømt panel
            typeInt = true; // skal nå håndtere int
            pane.setCenter(view); // viser instans tilpasset int
        });
        tb2.setOnAction(e -> { // tilsvarende for String :
            byttTreType();
            view2.displayTree();
            typeInt = false;
            pane.setCenter(view2);
        });
    }





    /**
     * Metoden kontrollerer for integer-input ved hjelp av unntak
     */
    private boolean erInteger() {
        try {
            Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException e) {
            view.displayTree();
            view.setStatus("Illegal Key, Integers only");
            tfKey.clear();
            return false;
        }
        return true;
    }





    /**
     * Insert av integer-verdier
     */
    private void insertInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.søk(key)) {
            view.displayTree();
            view.setStatus(key + " is already in the tree, new insert failed");
        } else {
            intTre.leggInn(key); // Insert a new key
            view.displayTree();
            view.setStatus(key + " is inserted in the tree");
        }
    }





    /**
     * Metode for å søke etter Integer-verdier
     */
    private void searchInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.søk(key)) { // key is in the tree OK
            view.displayTree();
            view.setStatus(key + " is in the tree");
        } else {
            view.displayTree();
            view.setStatus(key + " is not found");
        }
    }





    /**
     * Metode for å søke etter Integer-verdier
     *
     */
    private void findInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.find(key) != null) {
            view.displayTree();
            view.setStatus("the " + key + "th smallest value is " + intTre.find(key));
        } else {
            view.displayTree();
            view.setStatus(key + " is outside of range : max = number of nodes");
        }
    }



    
    

    /**
     * Metode for å slette Integer-verdier
     */
    private void deleteInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (!intTre.søk(key)) {
            view.displayTree();
            view.setStatus(key + " is not found");
        } else {
            intTre.slett(key); // Delete a key
            view.displayTree();
            view.setStatus(key + " is deleted from the tree");
        }
    }



    
    

    /**
     * Metoden kontrollerer for lovlig String
     * Tall og tom streng ikke lovlig, kan justeres i isNumericOrEmpty()
     *
     * @return
     */
    private boolean erString() {
        String key = tfKey.getText();
        if (erTallellerTom(key)) { // Tall og tom streng ikke lovlig
            view2.displayTree();
            view2.setStatus("Illegal Key, Strings only");
            tfKey.clear();
            return false;
        }
        return true;
    }




    

    /**
    * Metode for insert når String verdier
    */
    private void insertString() {
        String key = tfKey.getText();
        if (stringTre.søk(key)) {
            view2.displayTree();
            view2.setStatus(key + " is already in the tree, new insert failed");
        } else {
            stringTre.leggInn(key); // Insert a new key
            view2.displayTree();
            view2.setStatus(key + " is inserted in the tree");
        }
    }


    
    


    /**
     * Metode for å søke etter String-verdier
     */
    private void searchString() {
        String key = tfKey.getText();
        if (stringTre.søk(key)) {
            view2.displayTree();
            view2.setStatus(key + " is in the tree");
        } else {
            view2.displayTree();
            view2.setStatus(key + " is not found");
        }
    }




    /**
     * Metode for å søke etter Integer-verdier
     *
     */
    private void findString() {
        int key = Integer.parseInt(tfKey.getText());
        if (stringTre.find(key) != null) {
            view2.displayTree();
            view2.setStatus("the " + key + "th smallest value is " + stringTre.find(key));
        } else {
            view2.displayTree();
            view2.setStatus(key + " is outside of range : max = number of nodes");
        }
    }


    


    /**
     * Metode for å slette String-verdier
     */
    private void deleteString() {
        String key = tfKey.getText();
        if (!stringTre.søk(key)) {
            view2.displayTree();
            view2.setStatus(key + " is not in the tree");
        } else {
            stringTre.slett(key); // Delete a key
            view2.displayTree();
            view2.setStatus(key + " is deleted from the tree");
        }
    }






    /**
     * Metode for å teste Integer-tre. Setter inn 10 random Integers
     */
    private void testInteger() {
        for (int i=0; i<10; i++) {
            int key = new Random().nextInt(100);
            // TODO: søke først for duplikater : om man vil ha tilbakemelding til bruker
            // TODO: kan vi utelate søk fra insert ?
            intTre.leggInn(key); // Insert a new key
            view.displayTree();
            view.setStatus("Test Integer executed");
        }
    }


    
    

    /**
     * Metode for å teste Integer-tre. Setter inn 10 random Strings
     */
    private void testString() {
        for (int i=0; i<10; i++) {
            String key = lagRandomString();
            stringTre.leggInn(key); // Insert a new key
            view2.displayTree();
            view2.setStatus("Test String executed");
        }
    }






    /**
     * Hjelpemetoden returnerer toggleGroup i HBox
     *
     * @return
     */
    private HBox toggleDatatype() {
        ToggleGroup gruppe = new ToggleGroup();
        tb1.setToggleGroup(gruppe);
        tb2.setToggleGroup(gruppe);
        gruppe.selectToggle(typeInt ? tb1 : tb2);
        HBox ramme = new HBox(5);
        ramme.setPadding(new Insets(8, 0, 8, 0));
        ramme.getChildren().addAll(tb1, tb2);
        ramme.setAlignment(Pos.CENTER);
        return ramme;
    }





    /**
     * Hjelpemetode for å slette innhold i trær og tekstfelt
     */
    private void byttTreType() {
        intTre.clear(); // tøm tre
        stringTre.clear();
        tfKey.clear(); // tøm tekstfelt
    }

    
    
    

    /**
     * Hjelpemetode for å generere random permutasjoner av tre tilfeldige små bokstaver.
     *
     * @return String-verdi for insert i AVLTree<String>
     */
    private String lagRandomString() {
        int fra = 97; // fra liten a
        int til = 122; // til liten z - dropper æ, ø, å
        int antallTegn = 3;
        Random random = new Random();
        String testStreng = random.ints(fra, til + 1)
                .limit(antallTegn) // innfører grense på antall tegn, Stringbuilder får ascii
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return testStreng;
    }


    


    /**
     * Hjelpemtode for å avgjøre om en verdi er numerisk eller ikke
     * Tillater ikke numeriske verdier i AVLTree for String, heller ikke tom streng/ null
     *
     * @param verdi for test
     * @return true = String
     */
    private static boolean erTallellerTom(String verdi) {
        if (verdi == null || verdi.equals(""))
            return true;
        try {
            double d = Double.parseDouble(verdi);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }





    /**
    * Metode for å teste at AVLTree fungerer som tiltenkt
    */
    private void testAVLTree() {
        AVLTree<Integer> tree2 = new AVLTree<>(new Integer[] {25, 20, 5});
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
            System.out.print(e + " ");
        }
    }




    /**
    * Hjelpemetode for å skrive testresultater til konsoll
    */
    private static void printTree(AVLTree<Integer> tree2) {
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
