// -------------------------------------------------
// classe UsuarioDBDAO.java
// -------------------------------------------------
package br.com.quiz.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.quiz.quiz.model.Usuario;

public class UsuarioDBDAO implements UsuarioDAO, IConst {

    public void insere(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (username, senha) VALUES (?, ?) RETURNING id_usuario";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, String.valueOf(usuario), senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt("id_usuario"));  // Define o ID gerado no objeto
                }
            }
        }
    }

    public void atualiza(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET username = ?, senha = ?, score = ? WHERE id_usuario = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, String.valueOf(usuario), senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setFloat(3, usuario.getScore_time());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void remove(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, String.valueOf(usuario), senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public Usuario buscaPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("senha"),
                            rs.getFloat("score")
                    );
                    return u;
                }
            }
        }
        return null;
    }

    public Usuario buscaPorUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE username = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("senha"),
                            rs.getFloat("score")
                    );
                    return u;
                }
            }
        }
        return null;
    }

    public Usuario login(String username, String senha) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE username = ? AND senha = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("senha"),
                            rs.getFloat("score")
                    );
                    atualizaUltimoLogin(u.getId());  // Atualiza último login após login bem-sucedido
                    return u;
                }
            }
        }
        return null;  // Login falhou
    }

    public List<Usuario> listaTodos() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sql2 = "SELECT COALESCE(SUM(tempo_gasto), 0) AS total_score FROM RespostaUsuario WHERE id_usuario = ?";

                try (PreparedStatement stmt2 = con.prepareStatement(sql2)) {
                    stmt2.setInt(1, rs.getInt("id_usuario"));

                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        double scoreTotal = 0.0;
                        if (rs2.next()) {
                            scoreTotal = rs2.getDouble("total_score");
                        }

                        Usuario u = new Usuario(
                                rs.getInt("id_usuario"),
                                rs.getString("username"),
                                rs.getString("senha"),
                                (float) scoreTotal
                        );
                        usuarios.add(u);
                    }
                }
            }
        }
        return usuarios;
    }

    public void atualizaUltimoLogin(int idUsuario) throws SQLException {
        String sql = "UPDATE Usuario SET ultimo_login = NOW() WHERE id_usuario = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }
}
