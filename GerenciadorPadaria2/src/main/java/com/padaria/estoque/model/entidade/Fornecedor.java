package com.padaria.estoque.model.entidade;

public class Fornecedor {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cidade;

    public Fornecedor() {}

    public Fornecedor(int id, String nome, String telefone, String email, String cidade) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    @Override
    public String toString() {
        return nome + " (" + cidade + ")";
    }
}
