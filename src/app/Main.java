package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL_JDBC_PADRAO =  "jdbc:sqlite:database.db";


    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL_JDBC_PADRAO);
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
