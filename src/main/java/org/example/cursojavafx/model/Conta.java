package org.example.cursojavafx.model;

public class Conta {
    private Paciente paciente;
    private double valor;
    private boolean pago;


    public Conta(Paciente paciente, double valor){
        this.valor = valor;
        this.paciente = paciente;
        this.pago = false;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
