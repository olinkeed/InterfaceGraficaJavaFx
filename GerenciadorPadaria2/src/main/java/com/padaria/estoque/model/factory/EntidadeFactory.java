package com.padaria.estoque.model.factory;

import com.padaria.estoque.model.entidade.Fornecedor;
import com.padaria.estoque.model.entidade.Produto;

public class EntidadeFactory {

    public static Object criar(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "produto" -> new Produto();
            case "fornecedor" -> new Fornecedor();
            default -> throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        };
    }
}
