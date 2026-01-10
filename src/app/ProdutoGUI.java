package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//COMPILAR:  javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d out src/app/*.java
//EXECUTAR:  java \ --module-path $PATH_TO_FX \ --add-modules javafx.controls,javafx.fxml \ -cp "out:lib/*" \ app.ProdutoGUI


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;



import java.util.List;

public class ProdutoGUI extends Application {
	private ProdutoDAO produtoDAO;
	private ObservableList<Produto> produtos;
	private TableView<Produto> tableView;

	private TextField nomeInput, quantidadeInput, precoInput;
	private ComboBox<String> statusComboBox;
	private Connection conexaoDB;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage palco){
		conexaoDB = ConexaoDB.conectar();
		produtoDAO = new ProdutoDAO(conexaoDB); // Inicializa o DAO
		produtos = FXCollections.observableArrayList(produtoDAO.listarTodos()); // Carrega todos os produtos do BD
		
		palco.setTitle("Gerenciador de Estoque de Produtos - JavaFX");

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);

		// Label e Input do nome do produto
		HBox nomeProdutoHbox = new HBox();
		nomeProdutoHbox.setSpacing(10);
		Label nomeLabel = new Label("Produto: ");
		nomeInput = new TextField();
		nomeProdutoHbox.getChildren().addAll(nomeLabel,nomeInput);

		// Label e Input da Quantidade do produto
		HBox quantidadeHbox = new HBox();
		quantidadeHbox.setSpacing(10);
		Label quantidadeLabel = new Label("Quantidade: ");
		quantidadeInput = new TextField();
		quantidadeHbox.getChildren().addAll(quantidadeLabel,quantidadeInput);

		// Label e Input do preço do produto
		HBox precoHbox = new HBox();
		precoHbox.setSpacing(10);
		Label precoLabel = new Label("Preço: ");
		precoInput = new TextField();
		precoHbox.getChildren().addAll(precoLabel,precoInput);

		// ComboBox do Status do produto
		HBox statusHbox = new HBox();
		statusHbox.setSpacing(10);
		Label statusLabel = new Label("Status: ");
		statusComboBox = new ComboBox<>();
		statusComboBox.getItems().addAll("Estoque Normal", "Em Falta", "Estoque Baixo");
		statusHbox.getChildren().addAll(statusLabel,statusComboBox);

		// Botão Adicionar
		Button addButton = new Button("Adicionar");
		addButton.setOnAction(e ->{

			String preco = precoInput.getText().replace(',','.'); //onde tiver ',' colocar ponto

			Produto produto = new Produto(nomeInput.getText(),
				Integer.parseInt(quantidadeInput.getText()),
				Double.parseDouble(preco),
				statusComboBox.getValue());//Atributos do produto adicionado

			produtoDAO.inserir(produto);// Inserir os atributos do produto no BD
			produtos.setAll(produtoDAO.listarTodos()); // Atualizar a lista de produtos na tela
			limparCampos(); // Limpas os campos de entrada para nova digitalização
		});

		// Botão Atualizar
		Button updateButton = new Button("Atualizar");
		updateButton.setOnAction(e ->{
			Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();// Obtem produto selecionado
			if (selectedProduto != null) {

				//Settando atributos com os metodos SETTERS da classe produto
				selectedProduto.setNome(nomeInput.getText());

				selectedProduto.setQuantidade(Integer.parseInt(quantidadeInput.getText()));

				String preco = precoInput.getText().replace(',','.');//onde tiver ',' colocar ponto
				selectedProduto.setPreco(Double.parseDouble(preco));

				selectedProduto.setStatus(statusComboBox.getValue());
			}

			produtoDAO.atualizar(selectedProduto);// Inserir os atributos do produto no BD
			produtos.setAll(produtoDAO.listarTodos()); // Atualizar a lista de produtos na tela
			limparCampos(); // Limpas os campos de entrada para nova digitalização
		});

		// Botão Excluir
		Button deleteButton = new Button("Excluir");
		deleteButton.setOnAction(e ->{
			Produto selectedProduto = tableView.getSelectionModel().getSelectedItem();// Obtem produto selecionado
			if (selectedProduto != null) {
				produtoDAO.excluir(selectedProduto.getId());// excluir produto do BD
				produtos.setAll(produtoDAO.listarTodos()); // Atualizar a lista de produtos na tela
				limparCampos(); // Limpas os campos de entrada para nova digitalização
			}
		});
		deleteButton.getStyleClass().add("excluir");


		// Botão Limpar 
		Button clearButton = new Button("Limpar Campos");
		clearButton.setOnAction(e -> limparCampos());// Limpas os campos de entrada para nova digitalização

		//Tabela Produtos
		tableView = new TableView<>();
		tableView.setItems(produtos); // Define a lista de produtos na tabela
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS); //AJUSTA O TAMANHO DAS COLUNAS
		List<TableColumn<Produto, ?>> columns = List.of(
			criarColuna("ID", "id"),
			criarColuna("Produto", "nome"),
			criarColuna("Quantidade", "quantidade"),
			criarColuna("Preço", "preco"),
			criarColuna("Status", "status")
		);

		tableView.getColumns().addAll(columns);
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if (newSelection != null) {
				nomeInput.setText(newSelection.getNome());
				quantidadeInput.setText(String.valueOf(newSelection.getQuantidade()));
				precoInput.setText(String.valueOf(newSelection.getPreco()));
				statusComboBox.setValue(newSelection.getStatus());
			}
		});

	HBox buttonBox = new HBox();
	buttonBox.setSpacing(10);
	buttonBox.getChildren().addAll(addButton, updateButton, deleteButton,clearButton);// Box dos botões

	vbox.getChildren().addAll(nomeProdutoHbox,quantidadeHbox,precoHbox,statusHbox,buttonBox, tableView);// Layout Final

	Scene cena = new Scene(vbox,800,600);
	cena.getStylesheets().add("styles-produtos.css");//Folha de estilos
	palco.setScene(cena);
	palco.show();

	}

	// Metodo Stop: automaticamente chamado quando o sitema é encerrado!
	@Override
	public void stop(){
		try{
			conexaoDB.close(); //Fechar conexão com o db
		}catch(SQLException e){
			System.err.println("Erro ao fechar conexão: " + e.getMessage());
		}
	}

	// metodo limpar campos do formulario
	private void limparCampos(){
		nomeInput.clear();
		quantidadeInput.clear();
		precoInput.clear();
		statusComboBox.setValue(null);
	}

	// Metodo Criar colunas para tableView
	private TableColumn<Produto,String> criarColuna(String title, String property){
		TableColumn<Produto, String> col = new TableColumn<>(title);
		col.setCellValueFactory(new PropertyValueFactory<>(property));

		return col;
	}
}