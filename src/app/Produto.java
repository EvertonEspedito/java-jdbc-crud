package app;

// Compilar: javac -cp "lib/*" -d out src/app/ConexaoDB.java src/app/CriadorTabela.java src/app/Produto.java

public class Produto{
	private int id;
	private String nome;
	private int quantidade;
	private double preco;
	private String status;


	//Construtor, não precisa colocar o ID, pois o mesmo vai ser gerado automaticamente 
	public Produto(String nome, int quantidade, double preco, String status){
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.status = status;
	}

	public Produto(){
		//Construtor vazio
	}

	//Metodo Getter para ID
	public int getId(){
		return id;
	}
	//Metodo Setter para ID
	public void setId(int id){
		this.id = id;
	}

	//Metodos Getter e Setter para NOME
	public String getNome(){
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}

	//Metodos Getter e Setter para QUANTIDADE
	public int getQuantidade(){
		return quantidade;
	}
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}

	//Metodos Getter e Setter para PREÇO
	public double getPreco(){
		return preco;
	}
	public void setPreco(double preco){
		this.preco = preco;
	}

	//Metodos Getter e Setter para STATUS
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}