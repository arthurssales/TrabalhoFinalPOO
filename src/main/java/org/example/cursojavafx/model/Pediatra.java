package org.example.cursojavafx.model;

public class Pediatra extends Medico {

    public Pediatra(String nome,String sobrenome,String email,String senha,String sexo,int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.valorConsulta = 250;//depende do plano que atende
        qntMaxConsulta = 2;
    }


}
