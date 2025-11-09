package br.com.quiz.quiz.dao;

import br.com.quiz.quiz.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    private UsuarioDBDAO usuarioDAO;
    private Usuario usuarioTeste; // Para garantir que um usuário exista para o login

    // Este método é executado antes de cada teste
    @BeforeEach
    void setup() throws SQLException {
        usuarioDAO = new UsuarioDBDAO();
        // Configurar um usuário para garantir que ele exista no banco
        usuarioTeste = new Usuario(9999, "Tester", "0145", 0);
        usuarioDAO.insere(usuarioTeste);
    }

    // TESTE 1: Login com Credenciais Válidas
    @Test
    void testLoginComCredenciaisValidas() throws SQLException {
        System.out.println("Executando Teste 1: Login Válido");

        // Simula o login com as credenciais que sabemos que existem
        Usuario usuarioLogado = usuarioDAO.login("user_valido", "senha123");
        assertNotNull(usuarioLogado, "Teste 1 Falhou: Usuário deveria ser retornado para credenciais válidas.");
        // Verifica se o nome de usuário está correto
        assertEquals("user_valido", usuarioLogado.getUsername(), "Teste 1 Falhou: Nome de usuário incorreto.");
    }

    // TESTE 2: Login com Senha Inválida
    @Test
    void testLoginComSenhaInvalida() throws SQLException {
        System.out.println("Executando Teste 2: Login Inválido");

        // Simula o login com a senha incorreta
        Usuario usuarioLogado = usuarioDAO.login("user_valido", "senha_errada");
        assertNull(usuarioLogado, "Teste 2 Falhou: Usuário deve ser nulo para senha incorreta.");
    }
}