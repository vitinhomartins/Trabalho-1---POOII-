package br.com.quiz.quiz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB implements IConst {
    public static Connection getConexao(String stringDeConexao, String usuario, String senha) {
        try {
            return DriverManager.getConnection(IConst.stringDeConexao, IConst.usuario, IConst.senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

