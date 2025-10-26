package com.padaria.estoque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    void abrirProdutos(ActionEvent event) {
        abrirTela("/view/produto-view.fxml", "Cadastro de Produtos");
    }

    @FXML
    void abrirFornecedores(ActionEvent event) {
        abrirTela("/view/fornecedor-view.fxml", "Cadastro de Fornecedores");
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void abrirTela(String caminhoFXML, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
