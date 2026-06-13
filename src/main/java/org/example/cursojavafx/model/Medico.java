package org.example.cursojavafx.model;


public abstract class Medico extends UsuarioCadastrado {
    protected double valorConsulta;
    protected int qntMaxConsulta;
    protected int qntConsultas;
    //protected ArrayList<>

    public Medico(String nome,String sobrenome,String email,String senha, String sexo,int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.qntConsultas = 0;
    }

    //public abstract boolean atenderPaciente();


    public int getQntMaxConsulta() {
        return qntMaxConsulta;
    }

    public void setQntMaxConsulta(int qntMaxConsulta) {
        this.qntMaxConsulta = qntMaxConsulta;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public int getQntConsultas() {
        return qntConsultas;
    }

    public void setQntConsultas(int qntConsultas) {
        this.qntConsultas = qntConsultas;
    }
}