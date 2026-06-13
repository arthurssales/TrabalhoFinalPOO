package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface Comandos {

    @FXML
    public void confirmar();

    @FXML
    public void voltar(ActionEvent event) throws IOException;
}
