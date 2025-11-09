package br.com.quiz.quiz.dao;

import br.com.quiz.quiz.model.Pergunta;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerguntaDBDAOTest {

    @Test
    public void testListaTodosPerguntas() throws SQLException {
        PerguntaDBDAO dao = new PerguntaDBDAO();
        List<Pergunta> perguntas = dao.listaTodos();
        assertNotNull(perguntas);
        assertTrue(perguntas.size() >= 0);
    }
}