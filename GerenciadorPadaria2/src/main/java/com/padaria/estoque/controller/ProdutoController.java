package com.padaria.estoque.controller;

import com.padaria.estoque.model.dao.ProdutoDAO;
import com.padaria.estoque.model.dao.FornecedorDAO;
import com.padaria.estoque.model.entidade.Produto;
import com.padaria.estoque.model.entidade.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ProdutoController {

    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TextField txtQuantidade;
    @FXML private TextField txtFornecedor;

    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;
    @FXML private TableColumn<Produto, Integer> colQuantidade;
    @FXML private TableColumn<Produto, String> colFornecedor;

    private final ProdutoDAO dao = new ProdutoDAO();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private ObservableList<Produto> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colPreco.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getPreco()));
        colQuantidade.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getQuantidade()));

        colFornecedor.setCellValueFactory(c -> {
            try {
                Fornecedor f = fornecedorDAO.buscarPorId(c.getValue().getIdFornecedor());
                return new javafx.beans.property.SimpleStringProperty(f.getNome());
            } catch (SQLException e) {
                return new javafx.beans.property.SimpleStringProperty("Erro");
            }
        });

        atualizarTabela();
    }

    @FXML
    void salvar() {
        try {
            Produto p = new Produto(0,
                    txtNome.getText(),
                    Double.parseDouble(txtPreco.getText()),
                    Integer.parseInt(txtQuantidade.getText()),
                    Integer.parseInt(txtFornecedor.getText())
            );
            dao.inserir(p);
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).show();
        }
    }

    @FXML
    void editar() {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setNome(txtNome.getText());
                selecionado.setPreco(Double.parseDouble(txtPreco.getText()));
                selecionado.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                selecionado.setIdFornecedor(Integer.parseInt(txtFornecedor.getText()));

                dao.atualizar(selecionado);
                limparCampos();
                atualizarTabela();

                new Alert(Alert.AlertType.INFORMATION, "Produto atualizado com sucesso!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Erro ao atualizar: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Selecione um produto para editar.").show();
        }
    }

    @FXML
    void excluir() {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                dao.deletar(selecionado.getId());
                atualizarTabela();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Erro ao excluir: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    void preencherCampos() {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            txtNome.setText(selecionado.getNome());
            txtPreco.setText(String.valueOf(selecionado.getPreco()));
            txtQuantidade.setText(String.valueOf(selecionado.getQuantidade()));
            txtFornecedor.setText(String.valueOf(selecionado.getIdFornecedor()));
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtPreco.clear();
        txtQuantidade.clear();
        txtFornecedor.clear();
    }

    private void atualizarTabela() {
        try {
            lista.setAll(dao.listar());
            tabelaProdutos.setItems(lista);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao carregar tabela: " + e.getMessage()).show();
        }
    }
}
