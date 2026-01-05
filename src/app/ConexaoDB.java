package app;


//Compilar: javac -cp "lib/*" -d out src/app/ConexaoDB.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL_JDBC_PADRAO =  "jdbc:sqlite:database.db";

    // Método padrão
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL_JDBC_PADRAO);
            //System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para conectar url, usuário e senha
    public static Connection conectarGenerico(String url, String usuario, String senha){
        try{
            return DriverManager.getConnection(url,usuario,senha);
        }catch(SQLException e){
            System.err.println("Erro ao conectar ao Banco de Dados"+ e.getMessage());
            return null;
        }
    }
}
