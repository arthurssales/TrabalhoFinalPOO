package org.example.cursojavafx.service;

import org.example.cursojavafx.model.Paciente;

import java.util.ArrayList;

public class TesteCadastro {
    public static ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();

    public static void cadastrar(Paciente paciente){
        pacientesCadastrados.add(paciente);
    }
}
