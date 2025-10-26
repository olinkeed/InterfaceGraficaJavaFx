package com.padaria.estoque.model.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {
    private static Connection conexao;

    private ConexaoBD() {}

    public static synchronized Connection getInstance() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try (InputStream is = ConexaoBD.class.getResourceAsStream("/application.properties")) {
                Properties props = new Properties();
                props.load(is);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.pass");

                conexao = DriverManager.getConnection(url, user, pass);
                conexao.setAutoCommit(true);
                System.out.println("✅ Conectado ao banco: " + url);
            } catch (Exception e) {
                throw new SQLException("Erro ao conectar: " + e.getMessage(), e);
            }
        }
        return conexao;
    }
}
