package org.example.cursojavafx.model;

import org.example.cursojavafx.service.ConsultaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Medico extends UsuarioCadastrado {
    protected double valorConsulta;
    protected int qntMaxConsulta;
    protected int qntConsultas;
    private List<Consulta> consultas = new ArrayList<>();
    protected Queue<Paciente> filaPacientes = new LinkedList<>();


    public Medico(String nome, String sobrenome, String email, String senha, String sexo, int idade){
        super(nome,sobrenome,email,senha,sexo,idade);
        this.qntConsultas = 0;
        this.qntMaxConsulta = 3;
    }

    public void adicionarConsulta(Consulta consulta){
        consultas.add(consulta);
    }

    public void adicionarFilaPaciente(Paciente paciente){
        filaPacientes.offer(paciente);
    }

    public Paciente proximoPaciente(){
        return filaPacientes.poll();
    }


    public boolean podeAtender(Paciente paciente, LocalDate data){
        long consultasNoDia = consultas.stream().filter(c -> c.getData().equals(data)).count();

        if(consultasNoDia >= qntMaxConsulta){
            filaPacientes.offer(paciente);

            ConsultaService.setAgendaLotada(true);
            ConsultaService.setPlanoValido(true);
            ConsultaService.setIdadeValida(true);

            return false;
        }
        ConsultaService.setIdadeValida(true);
        ConsultaService.setAgendaLotada(false);
        ConsultaService.setPlanoValido(true);

        return true;
    }

    public void cancelarConsulta(Consulta consulta){
        consultas.remove(consulta);

        if(!filaPacientes.isEmpty()){
            Paciente proximo = filaPacientes.poll();
            Consulta novaConsulta = new Consulta(proximo,this,consulta.getData());

            consultas.add(novaConsulta);
            proximo.adicionarConsulta(novaConsulta);

            System.out.println("Paciente" + proximo.getNome() + " promovido da lista de espera");

        }
    }
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

    /*public ArrayList<Integer> getAvaliacao() {
        return avaliacoes;
    }

    public void setAvaliacao(ArrayList<Integer> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }*/
    /*protected ArrayList<Integer> avaliacoes;
    protected ArrayList<Avaliacao> avaliacoes2 = new ArrayList<>();
    */
    /*protected int somaAvaliacao = 0;
    protected double media;
    */
    /*public double calcularMediaAvaliacao(){
        int indice;

        for(indice=0; indice< avaliacoes.toArray().length;indice++){
            somaAvaliacao += avaliacoes.get(indice);
        }

        media = (double) somaAvaliacao / avaliacoes.toArray().length;
        return media;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }*/

    public Queue<Paciente> getFilaPacientes() {
        return filaPacientes;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

}
