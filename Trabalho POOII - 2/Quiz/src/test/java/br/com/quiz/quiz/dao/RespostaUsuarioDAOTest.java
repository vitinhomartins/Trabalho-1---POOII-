package br.com.quiz.quiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import br.com.quiz.quiz.model.RespostaUsuario;  // Import adicionado para RespostaUsuario

public class RespostaUsuarioDAOTest {

    private RespostaUsuarioDBDAO respostaDAO;
    private int idUsuarioComAcertos = 1;
    private int idUsuarioSemAcertos = 999;

    @BeforeEach
    void setup() throws SQLException {
        respostaDAO = new RespostaUsuarioDBDAO();
    }

    // TESTE 3: Cálculo do Score com Acertos Registrados
    @Test
    void testCalculaScoreComAcertos() throws SQLException {
        System.out.println("Executando Teste 3: Cálculo do Score (Acertos)");

        int scoreCalculado = respostaDAO.calculaScore(idUsuarioComAcertos);

        assertEquals(3, scoreCalculado, "Teste 3 Falhou: O score calculado deve ser 3.");
    }

    // TESTE 4: Cálculo do Score para Usuário sem Acertos
    @Test
    void testCalculaScoreSemAcertos() throws SQLException {
        System.out.println("Executando Teste 4: Cálculo do Score (Sem Acertos)");

        int scoreCalculado = respostaDAO.calculaScore(idUsuarioSemAcertos);

        assertEquals(0, scoreCalculado, "Teste 4 Falhou: O score deve ser 0 para usuário sem acertos.");
    }

    @Test
    public void testInsereResposta() throws SQLException {
        RespostaUsuario resposta = new RespostaUsuario(
                1,
                1,
                'A',
                true,
                30.0
        );

        respostaDAO.insere(resposta);

        assertNotNull(resposta.getIdResposta(), "ID da resposta deve ser gerado após inserção.");
        assertTrue(resposta.getIdResposta() > 0, "ID da resposta deve ser maior que 0.");
    }
}
