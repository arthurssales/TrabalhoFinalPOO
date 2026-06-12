package org.example.cursojavafx.model;

import org.example.cursojavafx.conecction.ConexaoPacientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAO {

    public void cadastrar(Paciente paciente) {

        String sql = "INSERT INTO pacientes " + "(nome,sobrenome/*,cpf*/,email,sexo,senha, /*telefone*/) " + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoPacientes.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getSobrenome());
            //stmt.setInt(3, paciente.getCpf());
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getSexo());
            stmt.setString(5, paciente.getSenha());
            //stmt.setInt(7, paciente.getTelefone());
            /*stmt.setInt(8,paciente.getDiaNas());
            stmt.setInt(9,paciente.getMesNas());
            stmt.setInt(10,paciente.getAnoNas());*/

            stmt.executeUpdate();

            System.out.println("Paciente cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente.");
            e.printStackTrace();
        }
    }

    public boolean autenticar(String email, String senha) {
        String sql = "SELECT * FROM pacientes" + "WHERE email = ? AND senha = ?";

        try (Connection conn = ConexaoPacientes.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}