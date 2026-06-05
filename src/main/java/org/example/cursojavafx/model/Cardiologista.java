package org.example.cursojavafx.model;

public class Cardiologista extends Medico {

    public Cardiologista(String nome,String email,String senha,String sobrenome,String sexo){
        super(nome,email,senha,sobrenome,sexo);
        this.valorConsulta = 500; //depende do plano que atende
    }
}