package org.example.cursojavafx.model;

public class Pneumologista extends Medico {

    public Pneumologista(String nome,String email, String senha,String sobrenome,String sexo){
        super(nome,email,senha,sobrenome,sexo);
        this.valorConsulta = 200;
    }


}
