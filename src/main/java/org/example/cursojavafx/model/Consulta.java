package org.example.cursojavafx.model;

import org.example.cursojavafx.service.CadastroUsuarioService;

public class Consulta {
    //Medico medico = new Medico();
    CadastroUsuarioService pacientedao1;

    Paciente paciente = pacientedao1.getPacienteLogado();
}
