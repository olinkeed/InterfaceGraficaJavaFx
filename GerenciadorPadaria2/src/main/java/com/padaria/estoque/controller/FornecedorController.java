package com.padaria.estoque.controller;

import com.padaria.estoque.model.dao.FornecedorDAO;
import com.padaria.estoque.model.entidade.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class FornecedorController {

    @FXML private TextField txtNome;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtCidade;

    @FXML private TableView<Fornecedor> tabelaFornecedores;
    @FXML private TableColumn<Fornecedor, String> colNome;
    @FXML private TableColumn<Fornecedor, String> colCidade;
    @FXML private TableColumn<Fornecedor, String> colTelefone;
    @FXML private TableColumn<Fornecedor, String> colEmail;

    private final FornecedorDAO dao = new FornecedorDAO();
    private ObservableList<Fornecedor> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colCidade.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCidade()));
        colTelefone.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTelefone()));
        colEmail.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));
        atualizarTabela();
    }

    @FXML
    void salvar() {
        try {
            Fornecedor f = new Fornecedor(0,
                    txtNome.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtCidade.getText());
            dao.inserir(f);
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).show();
        }
    }

    @FXML
    void editar() {
        Fornecedor selecionado = tabelaFornecedores.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setNome(txtNome.getText());
                selecionado.setTelefone(txtTelefone.getText());
                selecionado.setEmail(txtEmail.getText());
                selecionado.setCidade(txtCidade.getText());

                dao.atualizar(selecionado);
                limparCampos();
                atualizarTabela();

                new Alert(Alert.AlertType.INFORMATION, "Fornecedor atualizado com sucesso!").show();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Erro ao atualizar: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Selecione um fornecedor para editar.").show();
        }
    }

    @FXML
    void excluir() {
        Fornecedor selecionado = tabelaFornecedores.getSelectionModel().getSelectedItem();
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
        Fornecedor selecionado = tabelaFornecedores.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            txtNome.setText(selecionado.getNome());
            txtTelefone.setText(selecionado.getTelefone());
            txtEmail.setText(selecionado.getEmail());
            txtCidade.setText(selecionado.getCidade());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtTelefone.clear();
        txtEmail.clear();
        txtCidade.clear();
    }

    private void atualizarTabela() {
        try {
            lista.setAll(dao.listar());
            tabelaFornecedores.setItems(lista);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao carregar tabela: " + e.getMessage()).show();
        }
    }
}
