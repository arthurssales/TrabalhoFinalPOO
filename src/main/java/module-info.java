module org.example.cursojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.cursojavafx to javafx.fxml;
    exports org.example.cursojavafx;
    exports org.example.cursojavafx.controller;
    opens org.example.cursojavafx.controller to javafx.fxml;
    exports org.example.cursojavafx.model;
    opens org.example.cursojavafx.model to javafx.fxml;
}