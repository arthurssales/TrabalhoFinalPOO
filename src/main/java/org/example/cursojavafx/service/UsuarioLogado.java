package org.example.cursojavafx.service;

import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.model.Paciente;

public class UsuarioLogado {
    private static Paciente pacienteLogado;
    private static Medico medicoLogado;

    public static Medico getMedicoLogado(){
        return medicoLogado;
    }

    public static void setMedicoLogado(Medico medico){
        medicoLogado = medico;
    }

    public static Paciente getPacienteLogado() {
        return pacienteLogado;
    }

    public static void setPacienteLogado(Paciente paciente) {
        pacienteLogado = paciente;
    }
}
