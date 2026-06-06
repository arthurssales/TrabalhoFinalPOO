package org.example.cursojavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.cursojavafx.model.Cardiologista;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.model.Pediatra;
import org.example.cursojavafx.model.Pneumologista;

import java.util.ArrayList;

public class CadastroMedico {
    private ArrayList<Medico> medicosCadastrados = new ArrayList<>();

    public CadastroMedico(){
        Medico medico1 = new Pediatra("","","","","");
        Medico medico2 = new Pneumologista("","","","","");
        Medico medico3 = new Cardiologista("","","","","");

    }

    @FXML
    Button confirmarCadastro;



}
