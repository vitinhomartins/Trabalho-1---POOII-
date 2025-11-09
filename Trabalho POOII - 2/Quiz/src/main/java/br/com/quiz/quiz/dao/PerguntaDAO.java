package br.com.quiz.quiz.dao;

import br.com.quiz.quiz.model.Pergunta;

import java.sql.SQLException;
import java.util.List;

public interface PerguntaDAO {
    public void insere(Pergunta pergunta) throws SQLException;
    public void atualiza(Pergunta pergunta) throws SQLException;
    public void remove(Pergunta pergunta) throws SQLException;
    public Pergunta buscaPorId(int idPergunta) throws SQLException;
    public List<Pergunta> listaTodos() throws SQLException;
}