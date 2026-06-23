module org.example.cursojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.compiler;
    requires jdk.dynalink;
    requires mysql.connector.j;

    opens org.example.cursojavafx to javafx.fxml;
    exports org.example.cursojavafx;
    exports org.example.cursojavafx.controller;
    opens org.example.cursojavafx.controller to javafx.fxml;
    exports org.example.cursojavafx.model;
    opens org.example.cursojavafx.model to javafx.fxml;
    exports org.example.cursojavafx.conecction;
    opens org.example.cursojavafx.conecction to javafx.fxml;
    exports org.example.cursojavafx.service;
    opens org.example.cursojavafx.service to javafx.fxml;
    exports org.example.cursojavafx.dao;
    opens org.example.cursojavafx.dao to javafx.fxml;
}