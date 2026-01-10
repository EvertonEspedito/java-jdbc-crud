# 📦 Gerenciador de Estoque – Java JDBC + JavaFX

Este projeto é uma aplicação **desktop** desenvolvida em **Java**, utilizando **JavaFX** para a interface gráfica e **JDBC** para persistência de dados em banco de dados relacional.

O sistema permite realizar um **CRUD completo (Create, Read, Update, Delete)** de produtos, sendo ideal para fins educacionais, portfólio e prática de integração entre interface gráfica e banco de dados.

---

## 🖥️ Funcionalidades

* ➕ Cadastro de produtos
* ✏️ Atualização de produtos
* ❌ Exclusão de produtos
* 📋 Listagem de produtos em tabela
* 🔍 Seleção de produtos para edição
* 💾 Persistência de dados via JDBC

Cada produto possui:

* ID
* Nome
* Quantidade
* Preço
* Status do estoque

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **JavaFX** (Interface gráfica)
* **JDBC** (Acesso a banco de dados)
* **Banco de Dados Relacional** (ex: SQLite / MySQL)
* **CSS** (Estilização da interface)

---

## 📁 Estrutura do Projeto

```
java-jdbc-crud/
├── lib/                 # Dependências externas (JDBC, JavaFX se necessário)
├── src/
│   └── app/
│       ├── ConexaoDB.java
│       ├── CriadorTabela.java
│       ├── Produto.java
│       ├── ProdutoDAO.java
│       ├── Main.java
│       └── ProdutoGUI.java
├── styles-produtos.css  # Folha de estilos JavaFX
├── out/                 # Arquivos compilados
└── README.md
```

---

## ⚙️ Pré-requisitos

* Java JDK 17 ou superior
* JavaFX SDK configurado
* Variável de ambiente `PATH_TO_FX` apontando para a pasta `lib` do JavaFX

Exemplo (Linux):

```bash
export PATH_TO_FX=/caminho/para/javafx/lib
```

---

## ▶️ Como Compilar

Execute o comando abaixo na raiz do projeto:

```bash
javac --module-path $PATH_TO_FX \
--add-modules javafx.controls,javafx.fxml \
-cp "lib/*" \
-d out src/app/*.java
```

---

## ▶️ Como Executar

Após a compilação:

```bash
java --module-path $PATH_TO_FX \
--add-modules javafx.controls,javafx.fxml \
-cp "lib/*:out" app.ProdutoGUI
```

---

## 🎨 Estilização

A interface utiliza um arquivo CSS externo (`styles-produtos.css`) para personalização visual.

Ele é carregado na aplicação através da classe `Scene`.

---

## 📚 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de:

* Praticar **CRUD com JDBC**
* Integrar **JavaFX com banco de dados**
* Aprender organização de código em camadas (DAO)
* Desenvolver aplicações desktop em Java
* Compor portfólio profissional

---

## 🚀 Próximas Melhorias (Ideias)

* Validação de campos
* Máscara para campo de preço
* Mensagens de confirmação (Alert)
* Pesquisa de produtos
* Geração de JAR executável
* Separação completa em padrão MVC

---

## 👨‍💻 Autor

**Everton Santos**
Estudante e Desenvolvedor Java

---

📌 *Projeto desenvolvido para fins educacionais e portfólio.*

