package org.example.cursojavafx.model;


public abstract class Medico extends UsuarioCadastrado {

    protected double valorConsulta;
    protected int consultorio;

    public Medico(String nome,String email,String senha,String sobrenome, String sexo){
        super(nome,email,senha,sobrenome,sexo);
    }


}