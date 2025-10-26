package com.padaria.estoque.model.entidade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testConstrutorEGetters() {
        // 1. Arrange (Preparar)
        Produto p = new Produto(1, "Pão Francês", 0.50, 100, 1);

        // 2. Act (Agir) - Não há ação, só verificação

        // 3. Assert (Verificar)
        assertEquals(1, p.getId());
        assertEquals("Pão Francês", p.getNome());
        assertEquals(0.50, p.getPreco());
        assertEquals(100, p.getQuantidade());
        assertEquals(1, p.getIdFornecedor());
    }

    @Test
    void testToString() {
        // 1. Arrange
        Produto p = new Produto(1, "Bolo de Chocolate", 25.0, 5, 2);

        // 2. Act
        String resultado = p.toString();

        // 3. Assert
        assertEquals("Bolo de Chocolate (Qtd: 5)", resultado);
}
}