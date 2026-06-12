package org.example.cursojavafx.service;

import org.example.cursojavafx.model.Medico;

import java.util.ArrayList;

public class MedicoDAO {
    private ArrayList<Medico> medicosCadastrados = new ArrayList<>();

    public void cadastrarMedico(Medico medico){
        medicosCadastrados.add(medico);
    }



    public ArrayList<Medico> getMedicosCadastrados() {
        return medicosCadastrados;
    }

}
