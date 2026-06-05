package org.example.cursojavafx.model;

public class Pediatra extends Medico {

    public Pediatra(String nome,String email,String senha,String sobrenome,String sexo){
        super(nome,email,senha,sobrenome,sexo);
        this.valorConsulta = 250;//depende do plano que atende
    }
}
