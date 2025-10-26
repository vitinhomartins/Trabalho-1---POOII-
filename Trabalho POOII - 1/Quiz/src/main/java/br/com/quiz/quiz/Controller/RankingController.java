package br.com.quiz.quiz.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import br.com.quiz.quiz.dao.UsuarioDAO;
import br.com.quiz.quiz.dao.UsuarioDBDAO;
import br.com.quiz.quiz.model.Usuario;
import java.util.Comparator;
import java.util.List;

public class RankingController {

    @FXML private GridPane gridRanking;
    private UsuarioDAO usuarioDAO = new UsuarioDBDAO();

    @FXML
    public void initialize() {
        carregarRanking();
    }

    private void carregarRanking() {
        try {
            List<Usuario> ranking = usuarioDAO.listaTodos();
            System.out.println(ranking);
            ranking.sort(Comparator.comparingDouble(Usuario::getScore_time).reversed());
            int row = 1;
            for (Usuario usuario : ranking) {
                gridRanking.add(new Label(String.valueOf(row)), 0, row);
                gridRanking.add(new Label(usuario.getUsername()), 1, row);
                gridRanking.add(new Label(String.format("%.2fs", usuario.getScore_time())), 2, row);
                row++;
                if (row > 10) break; // Top 10
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ranking.");
            e.printStackTrace();
        }
    }
}