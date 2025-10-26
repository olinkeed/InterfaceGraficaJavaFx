package com.padaria.estoque.model.entidade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FornecedorTest {

    @Test
    void testConstrutorEGetters() {
        // 1. Arrange (Preparar)
        Fornecedor f = new Fornecedor(1, "Farinha & Cia", "42-99999-8888", "contato@farinha.com", "Ponta Grossa");

        // 2. Act (Agir) - Sem ação necessária

        // 3. Assert (Verificar)
        assertEquals(1, f.getId());
        assertEquals("Farinha & Cia", f.getNome());
        assertEquals("42-99999-8888", f.getTelefone());
        assertEquals("contato@farinha.com", f.getEmail());
        assertEquals("Ponta Grossa", f.getCidade());
    }

    @Test
    void testSetters() {
        // 1. Arrange
        Fornecedor f = new Fornecedor(); // Construtor vazio

        // 2. Act
        f.setId(10);
        f.setNome("Laticínios Irati");
        f.setTelefone("42-3422-1122");
        f.setEmail("pedidos@laticinios.com");
        f.setCidade("Irati");

        // 3. Assert
        assertEquals(10, f.getId());
        assertEquals("Laticínios Irati", f.getNome());
        assertEquals("42-3422-1122", f.getTelefone());
        assertEquals("pedidos@laticinios.com", f.getEmail());
        assertEquals("Irati", f.getCidade());
    }

    @Test
    void testToString() {
        // 1. Arrange
        Fornecedor f = new Fornecedor(5, "Ovos da Granja", "11-5555-4444", "ovos@granja.com", "São Paulo");

        // 2. Act
        String resultado = f.toString();

        // 3. Assert
        assertEquals("Ovos da Granja (São Paulo)", resultado);
}
}