module tree.avltree {
    requires javafx.controls;
    requires javafx.fxml;


    opens tree.avltree to javafx.fxml;
    exports tree.avltree;
}