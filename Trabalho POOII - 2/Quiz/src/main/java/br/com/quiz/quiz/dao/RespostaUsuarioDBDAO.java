// -------------------------------------------------
// classe RespostaUsuarioDBDAO.java
// -------------------------------------------------
package br.com.quiz.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.com.quiz.quiz.model.RespostaUsuario;

public class RespostaUsuarioDBDAO implements RespostaUsuarioDAO, IConst {

    public void insere(RespostaUsuario resposta) throws SQLException {
        String sql = "INSERT INTO RespostaUsuario (id_usuario, id_pergunta, resposta_escolhida, correta, data_resposta, tempo_gasto) VALUES (?, ?, ?, ?, ?, ?) RETURNING id_resposta";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, resposta.getIdUsuario());
            stmt.setInt(2, resposta.getIdPergunta());
            stmt.setString(3, String.valueOf(resposta.getRespostaEscolhida()));
            stmt.setBoolean(4, resposta.isCorreta());
            stmt.setTimestamp(5, resposta.getDataResposta() != null ? resposta.getDataResposta() : new Timestamp(System.currentTimeMillis()));
            stmt.setDouble(6, resposta.getTempoGasto());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    resposta.setIdResposta(rs.getInt("id_resposta"));
                }
            }
        }
    }

    public List<RespostaUsuario> buscaPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM RespostaUsuario WHERE id_usuario = ? ORDER BY data_resposta DESC";
        List<RespostaUsuario> respostas = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RespostaUsuario r = new RespostaUsuario();
                    r.setIdResposta(rs.getInt("id_resposta"));
                    r.setIdUsuario(rs.getInt("id_usuario"));
                    r.setIdPergunta(rs.getInt("id_pergunta"));
                    r.setRespostaEscolhida(rs.getString("resposta_escolhida").charAt(0));
                    r.setCorreta(rs.getBoolean("correta"));
                    r.setDataResposta(rs.getTimestamp("data_resposta"));
                    r.setTempoGasto(rs.getDouble("tempo_gasto"));
                    respostas.add(r);
                }
            }
        }
        return respostas;
    }

    public int calculaScore(int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) AS score FROM RespostaUsuario WHERE id_usuario = ? AND correta = true";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("score");
                }
            }
        }
        return 0;
    }

    public double calculaTempoTotal(int idUsuario) throws SQLException {
        String sql = "SELECT SUM(tempo_gasto) AS tempo_total FROM RespostaUsuario WHERE id_usuario = ? AND correta = true";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("tempo_total");
                }
            }
        }
        return 0.0;
    }
}