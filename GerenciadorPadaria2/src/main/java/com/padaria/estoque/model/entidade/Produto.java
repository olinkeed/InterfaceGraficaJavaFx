package com.padaria.estoque.model.entidade;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private int idFornecedor;

    public Produto() {}

    public Produto(int id, String nome, double preco, int quantidade, int idFornecedor) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getIdFornecedor() { return idFornecedor; }
    public void setIdFornecedor(int idFornecedor) { this.idFornecedor = idFornecedor; }

    @Override
    public String toString() {
        return nome + " (Qtd: " + quantidade + ")";
    }
}
