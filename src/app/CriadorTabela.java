package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//Compiar javac \-cp "lib/*" \ -d out \ src/app/ConexaoDB.java src/app/CriadorTabela.java
//Compilar: javac -cp "lib/*" -d out src/app/CriadorTabela.java

public class CriadorTabela{
	public static void main(String[] args) {
		try(Connection conexao = ConexaoDB.conectar();
			Statement stmt = conexao.createStatement()){

			// Comando SQL para criar a tabela
			String comandoSQL = "CREATE TABLE produtos ("+
					"id_produto INTEGER PRIMARY KEY,"+
					"nome_produto TEXT NOT NULL,"+
					"quantidade INTEGER,"+
					"preco REAL,"+
					"status TEXT"+
					");";
			System.out.println(comandoSQL);

			//Executar Comando SQL
			stmt.execute(comandoSQL);
			System.out.println("Tabela 'Produtos' criada com sucesso!");
		}catch (SQLException e){
			System.err.println("Erro ao criar Tabela"+e.getMessage());
			e.printStackTrace();
		}
	}	
}