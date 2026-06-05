package org.example.cursojavafx.model;

public class Paciente extends UsuarioCadastrado {
    private int cpf;

    public Paciente(String nome, String email, String senha, String sobrenome, String sexo,int cpf) {
        super(nome,email, senha, sobrenome, sexo);
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
}