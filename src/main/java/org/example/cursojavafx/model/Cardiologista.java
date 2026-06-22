package org.example.cursojavafx.model;

public class Cardiologista extends Medico {

    public Cardiologista(String nome,String sobrenome,String email,String senha,String sexo,int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.valorConsulta = 500;
    }

    public void desmarcarConsulta(){
        /*VERIFICA SE EXISTE CONSULTA MARCADA*/



    }

}