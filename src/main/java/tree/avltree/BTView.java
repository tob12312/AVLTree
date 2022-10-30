package tree.avltree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


/**
 * Class BTView tegner binærtre rekursivt med data fra class BST og arvinger (AVLTree)
 * Er gjort generisk for trær og noder av flere typer
 */
public class BTView<T extends Comparable<T>> extends Pane {
    private final AVLTree<T> TREE; // referanse til AVLTree
    private final double RADIUS = 15; // Tree node radius
    private final double VGAP = 50; //





    /**
     * Konstruktør som tar inn AVLTree av generisk type
     *
     * @param tree AVLTree av generisk type
     */
    public BTView(AVLTree<T> tree) {
        this.TREE = tree;
    }





    /**
    * Metoden tegner oppdatert status
     *
    * @param msg tekst som skal vises
    */
    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }




    /**
     * Drivermetode for enklere kall og nullsjekk
     * Parameterløs dersom keyElement ikke er identifisert.
     *
     */
    public void displayTree() {
        this.getChildren().clear();
        if (TREE.getRoot() != null) {
            displayTree(TREE.getRoot(), null, getWidth() / 2, VGAP,
                    getWidth() / 4);
        }
    }




    /**
     * Drivermetode for enklere kall og nullsjekk, og for sammenligning mot keyElement.
     * Endret til å ta inn element/ verdi av generisk type.
     * Ikke nødvendig endringer for å løse Oblig2, men artig med litt farge.
     *
     */
    public void displayTree(T keyElement) {
        this.getChildren().clear();
        if (TREE.getRoot() != null) {
            displayTree(TREE.getRoot(), keyElement, getWidth() / 2, VGAP,
                 getWidth() / 4);
        }
    }





    /**
     * Tegner subtrær med rotnode plassert i x og y. Rekursivt, halvering av bredde.
     * Har lagt til kontroll av likhet for visuell bekreftelse på brukerinput
     * Kalles av to ulike drivermetoder, avhengig av behov for sammenligning mot key.
     *
     */
    private void displayTree(AVLTree.TreeNode<T> root, T keyElement,
        double x, double y, double hGap) {

        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + VGAP, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, keyElement,x - hGap, y + VGAP, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + VGAP, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, keyElement, x + hGap, y + VGAP, hGap / 2);
        }

        // Display a node, keyElement highlighted in green
        Circle circle = new Circle(x, y, RADIUS);
        circle.setFill(root.element.equals(keyElement) ? Color.LIMEGREEN : Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
            new Text(x - 4, y + 4, root.element + ""));
    }

} // slutt class BTView


