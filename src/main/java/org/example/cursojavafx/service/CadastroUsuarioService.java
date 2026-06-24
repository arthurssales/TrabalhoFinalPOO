package org.example.cursojavafx.service;

import org.example.cursojavafx.model.*;

import java.util.ArrayList;

public class CadastroUsuarioService {
    private static final ArrayList<UsuarioCadastrado> usuariosCadastrados = new ArrayList<>();

    private static final ArrayList<Medico> medicosPlano = new ArrayList<>();
    private static final ArrayList<Medico> medicosCadastrados = new ArrayList<>();
    private static final ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();

    private static final Paciente paciente1 = new Paciente("Arthur","dos Santos","a","1234","Masculino",20);
    private static final Paciente paciente2 = new Paciente("Eduardo","Castelo Branco","d","1234","Masculino",18);
    private static final Paciente paciente3 = new Paciente("Luiz","Uchôa Castro","l","1234","Masculino",18);

    private static final Cardiologista cardiologista1 = new Cardiologista("Eduardo","Lucas","1","1","Masculino",21,"Plano A");
    private static final Cardiologista cardiologista2 = new Cardiologista("Roberto","Pêra","2","1","Masculino",24,"Plano A");
    private static final Cardiologista cardiologista3 = new Cardiologista("Lucas","Tolentino","3","1","Masculino",28,"Sem plano");

    private static final Dermatologista dermatologista1 = new Dermatologista("Arthur","Sales","4","1234","Masculino",20,"Plano A");
    private static final Dermatologista dermatologista2 = new Dermatologista("Manoel","Gomes","5","1234","Masculino",56,"Plano A");
    private static final Dermatologista dermatologista3 = new Dermatologista("Cristiano","Aveiro","6","1234","Masculino",41,"Plano A");

    private static final Pediatra pediatra1 = new Pediatra("Luiz","Miguel","7","1234","Masculino",20,"Plano A");
    private static final Pediatra pediatra2 = new Pediatra("William","Afton","8","1234","Masculino",54,"Sem plano");
    private static final Pediatra pediatra3 = new Pediatra("Jhon","Marston","9","1234","Masculino",38,"Sem plano");

    private static int i = 0;

    public static void cadastroInicial() {
        i++;
        if (i == 1) {
            pacientesCadastrados.add(paciente1);
            pacientesCadastrados.add(paciente2);
            pacientesCadastrados.add(paciente3);

            medicosCadastrados.add(cardiologista1);
            medicosCadastrados.add(cardiologista2);
            medicosCadastrados.add(cardiologista3);

            medicosCadastrados.add(dermatologista1);
            medicosCadastrados.add(dermatologista2);
            medicosCadastrados.add(dermatologista3);

            medicosCadastrados.add(pediatra1);
            medicosCadastrados.add(pediatra2);
            medicosCadastrados.add(pediatra3);

            usuariosCadastrados.addAll(pacientesCadastrados);
            usuariosCadastrados.addAll(medicosCadastrados);

            medicosPlano.addAll(medicosCadastrados.stream().filter(m -> "Plano A".equals(m.getPlano())).toList());

            paciente1.setCpf("11111111111");
            paciente2.setCpf("22222222222");
            paciente3.setCpf("33333333333");
        }
    }

    public static boolean compararSenhas(String senha1, String senha2){
        if(senha1 == null || senha2 == null)
            return false;

        return senha1.equals(senha2);
    }

    public static boolean autenticarEmail(String email){
        for(UsuarioCadastrado usuario : usuariosCadastrados){
            if(usuario.getEmail().equals(email))
                return false;
        }
        return true;
    }

    // Retorna true se o CPF já estiver cadastrado em algum paciente
    public static boolean verificarCpf(String cpf){
        return pacientesCadastrados.stream().anyMatch(p -> p.getCpf().equals(cpf));
    }

    public static void cadastrar(UsuarioCadastrado usuarioCadastrado){
        usuariosCadastrados.add(usuarioCadastrado);

        if(usuarioCadastrado instanceof Paciente){
            pacientesCadastrados.add((Paciente) usuarioCadastrado);
        }

        else{
            medicosCadastrados.add((Medico) usuarioCadastrado);
            adicionarMedicoPlano((Medico) usuarioCadastrado);
        }
    }

    public static void adicionarMedicoPlano(Medico medico){
        if(medico.getPlano().equals("Plano A"))
            medicosPlano.add(medico);
    }

    public static ArrayList<Medico> getMedicosPlano(){
        return medicosPlano;
    }

    public static ArrayList<Paciente> getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public static ArrayList<Medico> getMedicosCadastrados() {
        return medicosCadastrados;
    }
}