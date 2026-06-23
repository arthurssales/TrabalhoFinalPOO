package org.example.cursojavafx.model;

public class Cardiologista extends Medico {

    public Cardiologista(String nome,String sobrenome,String email,String senha,String sexo,int idade,String plano){
        super(nome,sobrenome,email,senha,sexo,idade,plano);
        this.valorConsulta = 500;
    }


}