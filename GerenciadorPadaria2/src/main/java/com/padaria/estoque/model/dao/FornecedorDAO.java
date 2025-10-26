package com.padaria.estoque.model.dao;

import com.padaria.estoque.model.entidade.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void inserir(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO fornecedor (nome, telefone, email, cidade) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getCidade());
            ps.executeUpdate();
        }
    }

    public void atualizar(Fornecedor f) throws SQLException {
        String sql = "UPDATE fornecedor SET nome=?, telefone=?, email=?, cidade=? WHERE id=?";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setString(2, f.getTelefone());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getCidade());
            ps.setInt(5, f.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM fornecedor WHERE id=?";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Fornecedor> listar() throws SQLException {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor ORDER BY id";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cidade")
                );
                lista.add(f);
            }
        }
        return lista;
    }
    public Fornecedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE id=?";
        try (Connection con = ConexaoBD.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Fornecedor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getString("cidade")
                    );
                } else {
                    return null;
                }
            }
        }
    }

}
