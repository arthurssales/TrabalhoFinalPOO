package org.example.cursojavafx.model;

import org.example.cursojavafx.service.CadastroUsuarioService;

import java.text.DateFormat;
import java.time.LocalDate;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDate data;

    private boolean consultaRealizada;

    public Consulta(Paciente paciente, Medico medico, LocalDate data){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.consultaRealizada = false;
        paciente.adicionarConsulta(this);
    }
    private String sintomas;
    private String diagnostico;
    private String tratamento;
    private String receita;
    private String examesSolicitados;
    private double valorPago;
    private String observacoes;
    private boolean realizada = false;



    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public LocalDate getData() {
        return data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getExamesSolicitados() {
        return examesSolicitados;
    }

    public void setExamesSolicitados(String examesSolicitados) {
        this.examesSolicitados = examesSolicitados;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    //private String descricao;

}
