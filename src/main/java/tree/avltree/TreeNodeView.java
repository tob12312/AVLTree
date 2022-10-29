package tree.avltree;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class TreeNodeView extends Circle {

    public TreeNodeView(double x, double y, double radius, String innhold) {
        super(x, y, radius);
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(this, new Text(x - 4, y + 4, innhold));
    }
}
