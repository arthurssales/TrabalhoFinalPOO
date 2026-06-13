package org.example.cursojavafx.service;

import org.example.cursojavafx.model.*;

import java.util.ArrayList;

public class CadastroUsuarioService {
    private static final ArrayList<UsuarioCadastrado> usuariosCadastrados = new ArrayList<>();

    private Paciente pacienteLogado = null;
    private Medico medicoLogado = null;

    private static final ArrayList<Medico> medicosCadastrados = new ArrayList<>();
    private static ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();
    private static final ArrayList<Cardiologista> cardiologistasCadastrados = new ArrayList<>();
    private static final ArrayList<Dermatologista> dermatologistasCadastrados = new ArrayList<>();
    private static final ArrayList<Pediatra> pediatrasCadastrados = new ArrayList<>();

    private static final Paciente paciente1 = new Paciente("Arthur","dos Santos","arthur@gmail.com","1234","Masculino",20);
    private static final Paciente paciente2 = new Paciente("Eduardo","Castelo Branco","dudu@gmail.com","1234","Masculino",20);
    private static final Paciente paciente3 = new Paciente("Luiz","Miguel Castro","luiz@gmail.com","1234","Masculino",20);

    private static final Cardiologista medico1 = new Cardiologista("Cardiologista1","Lucas","duduM@gmail.com","1234","Masculino",20);
    private static final Dermatologista medico2 = new Dermatologista("Dermatologista1","Sales","arthurM@gmail.com","1234","Masculino",20);
    private static final Pediatra medico3 = new Pediatra("Pediatra1","luiz@gmail.com","1234","1234","Masculino",20);
    private static int i = 0;

    public static void cadastroInicial(){
        i++;
        if (i == 1){
            pacientesCadastrados.add(paciente1);
            pacientesCadastrados.add(paciente2);
            pacientesCadastrados.add(paciente3);

            cardiologistasCadastrados.add(medico1);
            dermatologistasCadastrados.add(medico2);
            pediatrasCadastrados.add(medico3);

            medicosCadastrados.add(medico1);
            medicosCadastrados.add(medico2);
            medicosCadastrados.add(medico3);

            usuariosCadastrados.add(paciente1);
            usuariosCadastrados.add(paciente2);
            usuariosCadastrados.add(paciente3);
            usuariosCadastrados.add(medico1);
            usuariosCadastrados.add(medico2);
            usuariosCadastrados.add(medico3);
        }
    }

    /*----------PARA PACIENTES------------*/
    public static boolean verificarCpf(int cpf){
        for(Paciente paciente : pacientesCadastrados){
            if(paciente.getCpf() == cpf)
                return false;
        }
        return true;
    }

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }

    public Paciente getPacienteLogado(){
        return pacienteLogado;
    }

    /*----------PARA TODOS----------*/
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

    public static void cadastrar(UsuarioCadastrado usuarioCadastrado){
        usuariosCadastrados.add(usuarioCadastrado);

        if(usuarioCadastrado instanceof Paciente){
            pacientesCadastrados.add((Paciente) usuarioCadastrado);
        }

        else if(usuarioCadastrado instanceof Cardiologista){
            medicosCadastrados.add((Medico) usuarioCadastrado);
            cardiologistasCadastrados.add((Cardiologista) usuarioCadastrado);
        }

        else if(usuarioCadastrado instanceof Dermatologista){
            medicosCadastrados.add((Medico) usuarioCadastrado);
            dermatologistasCadastrados.add((Dermatologista) usuarioCadastrado);
        }

        else if (usuarioCadastrado instanceof Pediatra){
            medicosCadastrados.add((Medico) usuarioCadastrado);
            pediatrasCadastrados.add((Pediatra) usuarioCadastrado);
        }

        System.out.println("\nCADASTRO DOS USUARIOS");
        for (UsuarioCadastrado usuario : usuariosCadastrados){
            System.out.printf("Nome completo: %s %s - email: %s - senha: %s - sexo: %s\n",
                    usuario.getNome(),
                    usuario.getSobrenome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getSexo());
        }

        System.out.println("\nCADASTRO DOS PACIENTES");
        for(Paciente paciente : pacientesCadastrados){
            System.out.printf("Nome completo: %s %s - email: %s - senha: %s - sexo: %s\n",
                    paciente.getNome(),
                    paciente.getSobrenome(),
                    paciente.getEmail(),
                    paciente.getSenha(),
                    paciente.getSexo());
        }

        System.out.println("\nCADASTRO DOS MÉDICOS");
        for(Medico medico : medicosCadastrados){
            System.out.printf("Nome completo: %s %s - email: %s - senha: %s - sexo: %s\n",
                    medico.getNome(),
                    medico.getSobrenome(),
                    medico.getEmail(),
                    medico.getSenha(),
                    medico.getSexo());
        }
    }

    /*-----------PARA OS MEDICOS-------------*/

    /*public static boolean verificarCrm(int crm){
        for(Medico medico : medicosCadastrados){
            if(medico.get)
        }

    }*/

    public static ArrayList<Paciente> getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public static ArrayList<Cardiologista> getCardiologistasCadastrados(){
        return  cardiologistasCadastrados;
    }

    public static ArrayList<Pediatra> getPediatrasCadastrados(){
        return pediatrasCadastrados;
    }

    public static ArrayList<Dermatologista> getDermatologistasCadastrados(){
        return dermatologistasCadastrados;
    }

    public static ArrayList<Medico> getMedicosCadastrados() {
        return medicosCadastrados;
    }

    public void setMedicoLogado(Medico medicoLogado){
        this.medicoLogado = medicoLogado;
    }

    public Medico getMedicoLogado(){
        return medicoLogado;
    }
}
