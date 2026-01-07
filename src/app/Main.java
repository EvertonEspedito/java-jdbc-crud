package app;

import java.sql.Connection;
import java.util.List;

// COMPILAR : javac -cp "lib/*" -d out src/app/ConexaoDB.java src/app/CriadorTabela.java src/app/Produto.java src/app/ProdutoDAO.java src/app/Main.java
// Executar : java -cp "out:lib/*" app.Main  

public class Main {
	public static void main(String[] args) {
		try(Connection conexao = ConexaoDB.conectar()){
			ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

			//Listar todos os produtos (deve esta vazio)
			mostrarProdutos(produtoDAO);

			//Exemplo de insersão de produto
			Produto novoProduto1 = new Produto("Notebook",3,1400.22,"Em estoque"); 
			Produto novoProduto2 = new Produto("Smartphone",15,1499.99,"Estoque baixo"); 
			Produto novoProduto3 = new Produto("Tablet",23,699.99,"Sem Estoque");

			//Insersão
			//produtoDAO.inserir(novoProduto1);
			//produtoDAO.inserir(novoProduto2);
			//produtoDAO.inserir(novoProduto3);

			//Exclusão
			//produtoDAO.excluir(id);
			//produtoDAO.excluir(id);
			//produtoDAO.excluir(id);

			//Listar todos os produtos (deve conter os 3 produtos)
			mostrarProdutos(produtoDAO);

			//Consulta por ID e mudar nome, consultar produto com o ID (2)
			Produto produtoConsultado = produtoDAO.consultarPorId(2);
			if (produtoConsultado != null) {
				System.out.println("Produto encontrado: ");
				System.out.println("-> " + produtoConsultado.getNome() + ":" +produtoConsultado.getPreco());
				//mudar nome
				System.out.println("Novo nome do produto:");
				produtoConsultado.setNome("Laptop");
				produtoDAO.atualizar(produtoConsultado);
				System.out.println("-> " + produtoConsultado.getNome() + ":" +produtoConsultado.getPreco());

			}else{
				System.out.println("Produto não encontrado.");
			}
		}catch(Exception e){
			System.err.println("Erro Geral: " + e.getMessage());
		}
	}

	//metodo "mostrarProdutos()"
	private static void mostrarProdutos(ProdutoDAO produtoDAO){
		List<Produto> todosProdutos = produtoDAO.listarTodos();
		if (todosProdutos.isEmpty()) {
			System.out.println("nenhum produto encontrado.");
		}else{
			System.out.println("Lista de produtos:");
			for (Produto p:todosProdutos) {
				System.out.println(p.getId() + " : " + p.getNome() + " : " + p.getQuantidade() + " : " + p.getPreco() + " : " + p.getStatus());
			}
		}
	}
}