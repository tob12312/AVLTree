package tree.avltree;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.*;

/**
 *
 *
 * Innlevering obligatorisk oppgave 2, Algoritmer og datastrukturer, USN Bø.
 *
 * Videreutvikling av klasser fra Introduction to Java Programming and Data Structures
 * Forfatter: Y.Daniel Liang.
 *
 * Videreutviklet av: Tore Broberg og Leonard Rygh, Oktober 2022.
 *
 *
 * Class BSTAnimation - applikasjonsklasse:
 * Inneholder kontrollere for AVLTree/ BTView.
 * Tegner et enkelt kontrollpanel og kjører applikasjonen fra Start().
 * Elementer som skal insertes i AVLTree<T> benytter typebestemte referanser og metoder.
 *
 */
public class BSTAnimation extends Application {

    private BorderPane pane;
    private Boolean typeInt;
    private AVLTree<Integer> intTre;
    private AVLTree<String> stringTre;
    private BTView<Integer> intView;
    private BTView<String> stringView;
    private TextField tfKey;
    private Button btInsert, btDelete, btSearch, btFind, btTest;
    private RadioButton tb1, tb2;



    /**
     * Start
     *
     * @param primaryStage scene
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

        // TODO: test av addAll - OK
        Integer[] aA = {1, 2, 3, 4, 5};
        Integer[] bB = {3, 4};
        intTre.addAll(List.of(aA));

        // TODO: test av retainAll - OK
    //    intTre.retainAll(List.of(bB));

        // TODO: test av containsAll - OK
        System.out.println("Test containsAll før remove: " + intTre.containsAll(List.of(aA)));

        // TODO: test av removeAll - OK
    //    intTre.removeAll(List.of(aA));
        System.out.println("Test containsAll ETTER remove: " + intTre.containsAll(List.of(aA)));


        // TODO: testing:
        /** test av compareTo */
        int test = 0;
        Integer gammel = 1;
        Integer ny = 2;
        if (ny.compareTo(gammel) > 0) test = ny;
        System.out.println("Test av compareTo: " + test);


        /** test av Comparator på String */
        String[] tabell = {"ddd", "cccc", "aa", "b"};
        java.util.ArrayList<String> t1 = new ArrayList<String>(List.of(tabell));

        for (String t: t1) {
            System.out.println("T1 usortert: "+t);
        }


        t1.sort(java.util.Comparator.naturalOrder());
        for (String t: t1) {
            System.out.println("T1 natural order: "+t);
        }
        Collections.shuffle(t1);

        t1.sort(null);
        for (String t: t1) {
            System.out.println("T1 natural order NULL: "+t);
        }
        Collections.shuffle(t1);

        t1.sort(java.util.Comparator.reverseOrder());
        for (String t: t1) {
            System.out.println("T1 reversert natural order!: "+t);
        }
        Collections.shuffle(t1);

        t1.sort(java.util.Comparator.comparing(String::length));
        for (String t: t1) {
            System.out.println("T1 sortert på lengde: "+t);
        }
        Collections.shuffle(t1);

        t1.sort(java.util.Comparator.comparing(String::length).reversed());
        for (String t: t1) {
            System.out.println("T1 sortert på lengde, reversert: "+t);
        }
        Collections.shuffle(t1);


        /** test av Comparator på Integer */
        Integer[] tall = {2, 3, 1, 7, 4, 9, 6, 5};
        java.util.ArrayList<Integer> t2 = new ArrayList<Integer>(List.of(tall));


        for (int t: t2) {
            System.out.println("T2 usortert: "+t);
        }
        Collections.shuffle(t2); // omrokkerer

        for (int t: t2) {
            System.out.println("T2 omrokkert: "+t);
        }
        Collections.shuffle(t2); // omrokkerer

        t2.sort(java.util.Comparator.naturalOrder());
        for (int t: t2) {
            System.out.println("T2 i natural order: "+t);
        }
        Collections.shuffle(t2); // omrokkerer

        t2.sort(java.util.Comparator.comparing(Integer::intValue));
        for (int t: t2) {
            System.out.println("T2 i Integer.intValue: "+t);
        }
        Collections.shuffle(t2); // omrokkerer

        t2.sort(java.util.Comparator.comparing(Integer::byteValue));
        for (int t: t2) {
            System.out.println("T2 i byteValue: "+t);
        }
        Collections.shuffle(t2); // omrokkerer

        t2.forEach(e-> System.out.println("test av forEach: "+e));

   //     test(tabell);
        // TODO: test av swap
        Object[] swapTab = {"any", 2, 3, 4, 5, 6, 7, 8.99};
        System.out.println("SwapArray før swap 2: " + Arrays.toString(swapTab));
        System.out.println("SwapArray etter swap 2: "+ Arrays.toString(swap(swapTab, 0, 7)));

        List<Object> testL = new ArrayList<>();
        testL.add("Morra di");
        testL.add(2);
        testL.add(3);
        testL.add(4);
        testL.add(5);
        testL.add(6);
        testL.add(7);
        testL.add(8);
        System.out.println("SwapListe før swap: " + testL);
     //   Collections.swap(testL, 0, 7);
        System.out.println("SwapListe etter swap: "+ swap(testL, 0, 7));

        // todo: test av insertionSort:

        int[] t4 = {6, 2, 3, 4, 5, 66, 7, 1};
        InsertionSort.insertionSort(t4);
        for (Integer heltall : t4)
            System.out.println("Test av insertionSort: "+ heltall);

        int[] t5 = {6, 2, 3, 4, 5, 66, 7, 1};
        BubbleSort.bubbleSort(t5);
        for (Integer heltall : t5)
            System.out.println("Test av bubbleSort: "+ heltall);

        int[] t6 = {6, 2, 3, 4, 5, 66, 7, 1};
        MergeSort.mergeSort(t6);
        for (Integer heltall : t6)
            System.out.println("Test av mergeSort: "+ heltall);

        int[] t7 = {6, 2, 3, 4, 5, 66, 7, 1};
        QuickSort.quickSort(t7);
        for (Integer heltall : t7)
            System.out.println("Test av quickSort: "+ heltall);

        ArrayList<Integer> t8 = new ArrayList<>();
        t8.add(1);
        t8.add(2);
        t8.add(3);

        for (Integer i : t8) System.out.println("test iterator: " + i);

        t8.forEach(e-> System.out.println("nå med lambda: "+e));

        // TODO: read data from file

        try {
            ReadData.readData();
        } catch (Exception e) {
            System.out.println("FEIL MED READDATA: " + e.getMessage());
        }


        // TODO: 2-dimensjonal array = rad først, så kolonne
        int[][] testDobbelArray = new int[10][10];

        // TODO: test av selectionSort:

        Integer[] tT = {2, 3, 1, 7, 4, 9, 6, 5};
        selectionSort(tT);
        System.out.println("TEST AV SELECTIONSORT:: " + Arrays.toString(tT));

        Integer[] iS = {2, 3, 1, 7, 4, 9, 6, 5};
        insertionSort(iS);
        System.out.println("TEST AV INSERTIONSORT:: " + Arrays.toString(iS));


        // TEST AV QUICKSORT
        Integer[] qS = {2, 3, 1, 7, 4, 9, 6, 5};
        // TODO: de tre comparatorene under er likeverdige for Integer (men første er universell??)
        quickSort(qS, (a, b) -> a-b);
        //quickSort(qS, Comparator.comparingInt(a -> a));  // kompilator vil heller ha denne
        //quickSort(qS, Comparator.naturalOrder()); // denne forstår Integer
        System.out.println("TEST AV QUICKSORT:: " + Arrays.toString(qS));

        // TEST SWAP2
        Integer[] s2 = {2, 3, 1, 7, 4, 9, 6, 5};
        System.out.println("TEST AV swap2 før sort:: " + Arrays.toString(s2));
        swap2(s2, 0, 5);
        System.out.println("TEST AV swap2 etter sort:: " + Arrays.toString(s2));

        perm("abc");
    }

    // DRIVERMETODE FOR PERMUTASJONER
    public void perm(String s) {
        perm(s.toCharArray(), 0);
    }

    // REKURSIV METODE FOR PERMUTASJONER
    public void perm(char[] s, int pos) {
        if (pos == s.length - 1) // hvis ferdig, skriv ut
            System.out.println("Kontroll av perm 1: " + Arrays.toString(s));
        for (int i = pos; i<s.length; i++) {
            bytt(s, i, pos); // bytt indeks 0
            perm(s, pos +1); // rekursivt kall, jobb mot slutt av tabel
            bytt(s, pos, i); // ny i 0

        }
    }

    // VARIANT AV SWAP FOR CHAR[]
    public void bytt(char[] t, int e1, int e2) {
        char tmp = t[e1];
        t[e1] = t[e2];
        t[e2] = tmp;
    }

    // DRIVERRUTINE FOR QUICKSORT
    public static <T> void quickSort(T[] a, Comparator<T> c) {
        quickSort(a, 0, a.length, c);
    }


    // Function to find the middle of three number
    public static int middleOfThree(int a, int b, int c) {
        // Checking for b
        if ((a < b && b < c) || (c < b && b < a))
            return b;

            // Checking for a
        else if ((b < a && a < c) || (c < a && a < b))
            return a;

        else
            return c;
    }


    // QUICKSORT
    public static <T> void quickSort(T[] a, int start, int end, Comparator<T> c) {
        if (end-start <= 0) return; // bace case
        int i = start; // start på tabell, typisk 0
        int j = end-1; // pivotelementet VAR her siste element. TODO: finn median-av-tre
    //    int mid = (i+slutt) /2; // finner midterste verdi
    //    int j = middleOfThree(i, mid, slutt);

        boolean movingI = true;
        while (i<j) { // om flere indekser i tabell
            if (c.compare(a[i], a[j]) >= 0) { // bytt også like!
                swap2(a, i, j); // bytt innhold i indekserte
                movingI = !movingI; // bytt retning
            }
            if (movingI) i++; else j--;  // les ny indeks
        } // ferdig med splitt, utfør rekursive kall
        quickSort(a, start, i, c); // sorterer de små
        quickSort(a, i+1, end, c); // sorterer de store
    }

    public static<T extends Comparable<? super T>> void selectionSort(T[] a) {
        for (int i = 0; i<a.length; i++) {
            int minPos = i;
            for (int j = i+1; j<a.length; j++) {
                if (a[j].compareTo(a[minPos]) < 0) {
                    minPos = j;
                }
                swap2(a, i, minPos);
            }
        }
    }

    public static<T extends Comparable<? super T>> void insertionSort(T[] a) {
        for (int j = 1; j<a.length; j++) {
            T key = a[j];
            int i = j-1;
            while ( (i > -1) && ( a[i].compareTo(key) > 0) ) {
                a[i+1] = a[i];
                i--;
            }
            a[i+1] = key;
        }
    }

    private static <T> void swap2(T[] tab, int e1, int e2) {
        T tmp = tab[e1]; // huske objekt i tmp var
        tab[e1] = tab[e2]; // reassign objekt fra
        tab[e2] = tmp; // reassign objekt til
    }

    private static Object[] swap(Object[] tab, int e1, int e2) {
        Object tmp = tab[e1]; // huske objekt i tmp var
        tab[e1] = tab[e2]; // reassign objekt fra
        tab[e2] = tmp; // reassign objekt til
        return tab;
    }

    private static List<Object> swap(List<Object> liste, int e1, int e2) {
        List<Object> l = liste;
        l.set(e1, l.set(e2, l.get(e1)));
        return l;
    }






    /**
     * Metode for å initiere variabler/ referanser
     * AVLTree benytter comparator for organisering.
     * For java.util.Comparator.naturalOrder() kan AVLTree initieres uten parameter.
     *
     */
    private void initVars() {
        typeInt = true; // int som default = true

        // Referanser til AVLTree for hver type, parameterløst kall gir java.util.Comparator
        intTre = new AVLTree<>(); // AVLTree for Integer
        stringTre = new AVLTree<>(); // AVLTree for String

        // Test av alternativ init med comparator som parameter - for egne datatyper/ klasser
        /*
        intTre = new AVLTree<Integer>(Comparator.naturalOrder()); // AVLTree for Integer
        stringTre = new AVLTree<String>(Comparator.naturalOrder()); // AVLTree for String
         */
        // Referanser til BTintView for hver type
        intView = new BTView<>(intTre); // view for Integer
        stringView = new BTView<>(stringTre); // view for String
    }




    /**
     * Metode for å bygge opp kontrollpanel
     */
    private void kontrollpanel() {
        pane = new BorderPane(); // rotpanel
        pane.setCenter(intView); // integer-intView = default
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
        btFind.setOnAction(e -> { // find kThe smallest element/ value
            if (typeInt && erInteger()) findInteger(); // find in Int-tre
            else if (!typeInt && erInteger()) findString(); // find in String-tre
            else { // non-integer input
                stringView.displayTree();
                stringView.setStatus("Illegal Key, Integers only");
            }
        });
        btTest.setOnAction(e -> { // test
            if (typeInt) testInteger(); // test +=10 random Integer
            else testString(); // test +=10 random String
            testAVLTree(); // test pattern in console
        });
    }





    /**
     * Eventhåndtering kontrollpanel Toggle
     * Metodekall avgjøres av datatype (Integer/ String)
     * Toggle fungerer som select og reset
     *
     */
    private void toggleEvents() {
        tb1.setOnAction(e -> { // Ved toggling til Integer :
            reset(); // hjelpemetode tømmer trær og tekstfelt
            typeInt = true; // skal nå håndtere int
            pane.setCenter(intView); // viser instans tilpasset int
        });
        tb2.setOnAction(e -> { // tilsvarende for String :
            reset();
            typeInt = false;
            pane.setCenter(stringView);
        });
    }





    /**
     * Hjelpemetode for å slette innhold i trær og tekstfelt
     */
    private void reset() {
        intTre.clear(); // tøm tre
        stringTre.clear();
        intView.getChildren().clear(); // tøm panel
        stringView.getChildren().clear();
        tfKey.clear(); // tøm tekstfelt
    }





    /**
     * Metoden kontrollerer for integer-input ved hjelp av unntak
     */
    private boolean erInteger() {
        try {
            Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException e) {
            intView.displayTree();
            intView.setStatus("Illegal Key, Integers only");
            tfKey.clear();
            return false;
        }
        return true;
    }





    /**
     * Insert av integer-verdier.
     * Duplikater ikke tillatt.
     */
    private void insertInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.søk(key)) {
            intView.displayTree();
            intView.setStatus(key + " is already in the tree, new insert failed");
        } else {
            intTre.leggInn(key); // Insert a new key
            intView.displayTree(key);
            intView.setStatus(key + " is inserted in the tree");
        }
    }





    /**
     * Metode for å søke etter Integer-verdier.
     */
    private void searchInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.søk(key)) { // key is in the tree OK
            intView.displayTree(key);
            intView.setStatus(key + " is in the tree");
        } else {
            intView.displayTree(key);
            intView.setStatus(key + " is not found");
        }
    }





    /**
     * Metode for å søke etter den K minste Integer-verdien
     *
     */
    private void findInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (intTre.find(key) != null) {
            int respons = intTre.find(key);
            intView.displayTree(respons);
            intView.setStatus("The " + key + "th smallest Key is " + respons);
        } else {
            intView.displayTree();
            intView.setStatus(key + " is outside of range : max = number of nodes");
        }
    }



    
    

    /**
     * Metode for å slette Integer-verdier
     */
    private void deleteInteger() {
        int key = Integer.parseInt(tfKey.getText());
        if (!intTre.søk(key)) {
            intView.displayTree();
            intView.setStatus(key + " is not found");
        } else {
            intTre.slett(key); // Key slettes
            intView.displayTree();
            intView.setStatus(key + " is deleted from the tree");
        }
    }



    
    

    /**
     * Metoden kontrollerer for lovlig String
     * Tall og tom streng ikke lovlig, kan justeres i isNumericOrEmpty()
     *
     * @return true hvis lovlig verdi
     */
    private boolean erString() {
        String key = tfKey.getText();
        if (erTallellerTom(key)) { // Tall og tom streng ikke lovlig
            stringView.displayTree();
            stringView.setStatus("Illegal Key, Strings only");
            tfKey.clear();
            return false;
        }
        return true;
    }





    

    /**
    * Metode for insert av String verdier
     * Duplikater ikke tillatt
    */
    private void insertString() {
        String key = tfKey.getText();
        if (stringTre.søk(key)) {
            stringView.displayTree();
            stringView.setStatus(key + " is already in the tree, new insert failed");
        } else {
            stringTre.leggInn(key); // Key settes inn
            stringView.displayTree(key);
            stringView.setStatus(key + " is inserted in the tree");
        }
    }


    
    


    /**
     * Metode for å søke etter String-verdier
     */
    private void searchString() {
        String key = tfKey.getText();
        if (stringTre.søk(key)) {
            stringView.displayTree(key);
            stringView.setStatus(key + " is in the tree");
        } else {
            stringView.displayTree(key);
            stringView.setStatus(key + " is not found");
        }
    }




    /**
     * Metode for å søke etter den K'te minste String-verdien
     *
     */
    private void findString() {
        int key = Integer.parseInt(tfKey.getText());
        if (stringTre.find(key) != null) {
            String response = stringTre.find(key);
            stringView.displayTree(response);
            stringView.setStatus("The " + key + "th smallest value is " + response);
        } else {
            stringView.displayTree();
            stringView.setStatus(key + " is outside of range : max = number of nodes");
        }
    }


    


    /**
     * Metode for å slette String-verdier
     */
    private void deleteString() {
        String key = tfKey.getText();
        if (!stringTre.søk(key)) {
            stringView.displayTree();
            stringView.setStatus(key + " is not in the tree");
        } else {
            stringTre.slett(key); // Key slettes
            stringView.displayTree();
            stringView.setStatus(key + " is deleted from the tree");
        }
    }






    /**
     * Metode for å teste Integer-tre. Setter inn 10 random Integers.
     * Setter inn tall fra 0 til 99. OBS! duplikater ikke tillatt.
     * Øk bounds for testing av flere nivåer.
     *
     */
    private void testInteger() {
        for (int i=0; i<10; i++) {
            int key = new Random().nextInt(100);
            intTre.leggInn(key); // Keys legges inn
            intView.displayTree();
            intView.setStatus("Test Integer executed");
        }
    }


    
    

    /**
     * Metode for å teste String-tre. Setter inn 10 Strings
     * bestående av tilfeldige permutasjoner av de fem første små bokstavene.
     * OBS! duplikater ikke tillatt.
     * Øk antall lovlige tegn for testing av flere nivåer.
     */
    private void testString() {
        for (int i=0; i<10; i++) {
            String key = lagRandomString();
            stringTre.leggInn(key); // Keys legges inn
            stringView.displayTree();
            stringView.setStatus("Test String executed");
        }
    }






    /**
     * Hjelpemetoden for kontrollpanel
     *
     * @return toggleGroup i Hbox
     */
    private HBox toggleDatatype() {
        ToggleGroup gruppe = new ToggleGroup();
        tb1.setToggleGroup(gruppe);
        tb2.setToggleGroup(gruppe);
        gruppe.selectToggle(typeInt ? tb1 : tb2);
        HBox ramme = new HBox(5);
        ramme.getChildren().addAll(tb1, tb2);
        ramme.setAlignment(Pos.CENTER);
        return ramme;
    }




    

    /**
     * Hjelpemetode for å generere randomiserte strenger bestående av små bokstaver
     * Begrenset til a, b, c, d, e
     *
     * @return String-verdi for insert i AVLTree<String>
     */
    private String lagRandomString() {
        int fra = 97; // fra aaa
        int til = 101; // tilOgMed eee
        int antallTegn = 3;
        Random random = new Random();
        return random.ints(fra, til + 1)
                .limit(antallTegn) // innfører grense på antall tegn, Stringbuilder får ascii
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    


    /**
     * Hjelpemtode for å begrense input av String-verdier
     * Tillater ikke numeriske verdier i AVLTree for String, heller ikke tom streng/ null
     * Kan utelates/ justeres
     *
     * @param verdi for test
     * @return true = String
     */
    private boolean erTallellerTom(String verdi) {
        if (verdi == null || verdi.equals(""))
            return true;
        try {
            Double.parseDouble(verdi);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }





    /**
    * Metode for å teste at AVLTree fungerer som tiltenkt, benytter egen instans av ALVTree
    */
    private void testAVLTree() {
        AVLTree<Integer> tree = new AVLTree<>(new Integer[] {25, 20, 5, 30, 40, 60});
        System.out.print("After inseting 25, 20, 5:");
        printTree(tree);
        tree.leggInn(34);
        tree.leggInn(50);
        System.out.print("\nAfter inserting 34, 50:");
        printTree(tree);
        tree.leggInn(30);
        System.out.print("\nAfter inserting 30:");
        printTree(tree);
        tree.leggInn(10);
        System.out.print("\nAfter inserting 10:");
        printTree(tree);
        tree.slett(30);
        tree.slett(34);
        tree.slett(50);
        System.out.print("\nAfter removing 34, 30, 50:");
        printTree(tree);
        tree.slett(5);
        System.out.print("\nAfter removing 5:");
        System.out.print("\nTraverse the elements in the tree: ");
        for (int e: tree) {
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

    private static void test(String[] tabell) {
      java.util.Arrays.sort(tabell, Comparator.comparing(String::length));
      for (String s: tabell)
        System.out.println("Sortert tabell: " + s);
    }

}
