package org.example.cursojavafx.conecction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConxaoPacientes {





    //private final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:/localhost:127.0.0.1/pacientes";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }


    /*public Connection getConnection(){
        try{
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException  |SQLException e) {
            throw new RuntimeException("Erro na conexão: ",e);
        }


    }

        public static void closeConnection(Connection con, PreparedStatement stmt){
            try{

            if (con != null){
                    con.close();
                }
            }catch (SQLException e){
                Logger.getLogger(ConnectionCadastroPacientes.class.getName()).log(Level.SEVERE,null,e);
            }
        }*/


//    Class.forName("com.mysql.jdbc.Driver");
//    Connection ConnectionCadastroPacientes = DriverManager.getConnection(nome,sobrenome,email,senha,cpf,);


}
