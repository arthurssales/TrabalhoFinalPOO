package org.example.cursojavafx.model;

import org.example.cursojavafx.service.ConsultaService;

import java.time.LocalDate;

public class Pediatra extends Medico {

    public Pediatra(String nome,String sobrenome,String email,String senha,String sexo,int idade,String plano){
        super(nome,sobrenome,email,senha,sexo,idade, plano);
        this.qntMaxConsulta = 2;
        this.valorConsulta = 200;
    }


    public boolean podeAtender(Paciente paciente, LocalDate data){
        if(paciente.getIdade() > 18){
            System.out.println("Idade inválida");
            ConsultaService.setIdadeValida(false);
            return false;
        }

        return super.podeAtender(paciente,data);
    }
}
