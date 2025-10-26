package com.padaria.estoque.model.dao;

import com.padaria.estoque.model.entidade.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto p) throws SQLException {
        String sql = "INSERT INTO produto (nome, preco, quantidade, id_fornecedor) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.setInt(4, p.getIdFornecedor());
            ps.executeUpdate();
        }
    }

    public void atualizar(Produto p) throws SQLException {
        String sql = "UPDATE produto SET nome=?, preco=?, quantidade=?, id_fornecedor=? WHERE id=?";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.setInt(4, p.getIdFornecedor());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id=?";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto ORDER BY id";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_fornecedor")
                );
                lista.add(p);
            }
        }
        return lista;
    }
}
