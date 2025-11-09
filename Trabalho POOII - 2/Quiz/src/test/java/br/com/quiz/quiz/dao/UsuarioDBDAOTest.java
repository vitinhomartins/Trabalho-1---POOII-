package br.com.quiz.quiz.dao;

import br.com.quiz.quiz.model.Usuario;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDBDAOTest {

    @Test
    public void testInsereUsuario() throws SQLException {
        Usuario usuario = new Usuario(-1, "testeUser", "senha123", 0.0f);
        UsuarioDBDAO dao = new UsuarioDBDAO();
        dao.insere(usuario);
        assertNotNull(usuario.getId());
        assertTrue(usuario.getId() > 0);
    }

    @Test
    public void testBuscaPorUsername() throws SQLException {
        UsuarioDBDAO dao = new UsuarioDBDAO();
        Usuario usuario = dao.buscaPorUsername("Vitinho");  // Assume que "testeUser" existe na DB
        assertNotNull(usuario);
        assertEquals("Isabellinha", usuario.getUsername());
    }
}