// -------------------------------------------------
// classe PerguntaDBDAO.java
// -------------------------------------------------
package br.com.quiz.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.quiz.quiz.model.Pergunta;

public class PerguntaDBDAO implements PerguntaDAO, IConst {

    public void insere(Pergunta pergunta) throws SQLException {
        String sql = "INSERT INTO Pergunta (texto_pergunta, numero_questao, opcao_a, opcao_b, opcao_c, opcao_d) VALUES (?, ?, ?, ?, ?, ?) RETURNING id_pergunta";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pergunta.getTextoPergunta());
            stmt.setInt(2, pergunta.getNumeroQuestao());
            stmt.setString(3, pergunta.getAlternativas()[0]);  // opcao_a
            stmt.setString(4, pergunta.getAlternativas()[1]);  // opcao_b
            stmt.setString(5, pergunta.getAlternativas()[2]);  // opcao_c
            stmt.setString(6, pergunta.getAlternativas()[3]);  // opcao_d
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                }
            }
        }
    }

    public void atualiza(Pergunta pergunta) throws SQLException {
        String sql = "UPDATE Pergunta SET texto_pergunta = ?, numero_questao = ?, opcao_a = ?, opcao_b = ?, opcao_c = ?, opcao_d = ? WHERE id_pergunta = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pergunta.getTextoPergunta());
            stmt.setInt(2, pergunta.getNumeroQuestao());
            stmt.setString(3, pergunta.getAlternativas()[0]);  // opcao_a
            stmt.setString(4, pergunta.getAlternativas()[1]);  // opcao_b
            stmt.setString(5, pergunta.getAlternativas()[2]);  // opcao_c
            stmt.setString(6, pergunta.getAlternativas()[3]);  // opcao_d
            stmt.setInt(7, pergunta.getPerguntaId());
            stmt.executeUpdate();
        }
    }

    public void remove(Pergunta pergunta) throws SQLException {
        String sql = "DELETE FROM Pergunta WHERE id_pergunta = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, pergunta.getPerguntaId());
            stmt.executeUpdate();
        }
    }

    public Pergunta buscaPorId(int idPergunta) throws SQLException {
        String sql = "SELECT * FROM Pergunta WHERE id_pergunta = ?";
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPergunta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String[] alternativas = {
                            rs.getString("opcao_a"),
                            rs.getString("opcao_b"),
                            rs.getString("opcao_c"),
                            rs.getString("opcao_d")
                    };
                    Pergunta p = new Pergunta(
                            rs.getInt("id_pergunta"),
                            rs.getString("texto_pergunta"),
                            rs.getInt("numero_questao"),
                            alternativas,
                            rs.getString("resposta_correta")
                    );
                    return p;
                }
            }
        }
        return null;
    }

    public List<Pergunta> listaTodos() throws SQLException {
        String sql = "SELECT * FROM Pergunta";
        List<Pergunta> perguntas = new ArrayList<>();
        try (Connection con = ConexaoDB.getConexao(stringDeConexao, usuario, senha);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String[] alternativas = {
                        rs.getString("opcao_a"),
                        rs.getString("opcao_b"),
                        rs.getString("opcao_c"),
                        rs.getString("opcao_d")
                };
                Pergunta p = new Pergunta(
                        rs.getInt("id_pergunta"),
                        rs.getString("texto_pergunta"),
                        rs.getInt("numero_questao"),
                        alternativas,
                        rs.getString("resposta_correta")
                );
                perguntas.add(p);
            }
        }
        return perguntas;
    }
}
