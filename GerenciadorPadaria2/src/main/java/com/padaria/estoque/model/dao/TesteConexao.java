package com.padaria.estoque.model.dao;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            ConexaoBD.getInstance();
            System.out.println("Conexão bem-sucedida!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
