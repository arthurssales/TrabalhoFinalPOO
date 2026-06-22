package org.example.cursojavafx.model;

import org.example.cursojavafx.service.ConsultaService;

import java.time.LocalDate;

public class Dermatologista extends Medico {

    public Dermatologista(String nome, String sobrenome, String email, String senha, String sexo, int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.valorConsulta = 200;
    }

    @Override
    public boolean podeAtender(Paciente paciente, LocalDate data){

        if(paciente.getPlanoSaude().equals("não tenho")) {
            System.out.println("Plano inválido!");
            ConsultaService.setPlanoValido(false);
            return false;
        }

        return super.podeAtender(paciente,data);
    }

}
