package org.example.cursojavafx.model;

public class Dermatologista extends Medico {

    public Dermatologista(String nome, String sobrenome, String email, String senha, String sexo, int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.valorConsulta = 200;
        qntMaxConsulta = 2;
    }
}
