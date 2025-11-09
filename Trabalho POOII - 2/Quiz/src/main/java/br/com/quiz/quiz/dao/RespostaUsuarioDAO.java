package br.com.quiz.quiz.dao;
import java.sql.SQLException;
import java.util.List;
import br.com.quiz.quiz.model.RespostaUsuario;

public interface RespostaUsuarioDAO {
    public void insere(RespostaUsuario resposta) throws SQLException;
    public List<RespostaUsuario> buscaPorUsuario(int idUsuario) throws SQLException;
    public int calculaScore(int idUsuario) throws SQLException;
    public double calculaTempoTotal(int idUsuario) throws SQLException;
}
