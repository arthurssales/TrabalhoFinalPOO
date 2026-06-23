package org.example.cursojavafx.service;

import org.example.cursojavafx.model.*;

import java.time.LocalDate;
import java.util.Comparator;

public class ConsultaService {
    private static boolean agendaLotada = false;
    private static boolean planoValido = true;
    private static boolean idadeValida = true;
    private static boolean dataValida = true;

    public static Consulta marcarConsulta(Paciente paciente, Medico medico, LocalDate data){
        if(data.isBefore(LocalDate.now())){
            dataValida = false;
            return null;
        }

        dataValida = true;
        if(!medico.podeAtender(paciente, data))
            return null;

        Consulta consulta = new Consulta(paciente, medico, data);

        medico.adicionarConsulta(consulta);
        paciente.adicionarConsulta(consulta);

        medico.setQntConsultas(medico.getQntConsultas() + 1);

        return consulta;
    }


    public static Conta realizarConsulta(Consulta consulta, String sintomas, String diagnostico, String tratamento,
                                         String medicamentos, String exames, String observacoes){

        consulta.setSintomas(sintomas);
        consulta.setDiagnostico(diagnostico);
        consulta.setTratamento(tratamento);
        consulta.setReceita(medicamentos);
        consulta.setExamesSolicitados(exames);
        consulta.setObservacoes(observacoes);

        Paciente paciente = consulta.getPaciente();

        if(!paciente.getPlanoSaude().equals("não tenho"))
            consulta.setValorPago(0);

        else
            consulta.setValorPago(consulta.getMedico().getValorConsulta());

        consulta.getMedico().getConsultasRealizadas().add(consulta);
        consulta.getMedico().getConsultasAgendadas().remove(consulta);

        consulta.getPaciente().getConsultasAgendadas().remove(consulta);
        consulta.getPaciente().getHistoricoConsultas().add(consulta);
        consulta.setConsultaRealizada(true);

        return new Conta(paciente, consulta.getValorPago());
    }

    public static Consulta buscarProximaConsulta(Medico medico){

        LocalDate hoje = LocalDate.now();

        return medico.getConsultasAgendadas()
                .stream()
                .filter(c -> !c.getData().isBefore(hoje))
                .sorted(Comparator.comparing(Consulta::getData))
                .findFirst()
                .orElse(null);
    }

    public static void setIdadeValida(boolean idadeValida) {
        ConsultaService.idadeValida = idadeValida;
    }

    public static void setPlanoValido(boolean planoValido) {
        ConsultaService.planoValido = planoValido;
    }

    public static void setAgendaLotada(boolean agendaLotada) {
        ConsultaService.agendaLotada = agendaLotada;
    }

    public static boolean isIdadeValida() {
        return idadeValida;
    }

    public static boolean isPlanoValido() {
        return planoValido;
    }

    public static boolean isAgendaLotada() {
        return agendaLotada;
    }
}
