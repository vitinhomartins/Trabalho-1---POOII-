package br.com.quiz.quiz.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MenuController {

    @FXML
    private void iniciarQuiz(ActionEvent event) {
        try {
            Parent quizParent = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaQuiz.fxml"));
            Scene quizScene = new Scene(quizParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(quizScene);
            window.setTitle("POO Gênio Quiz - O Jogo");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirRanking(ActionEvent event) {
        try {
            Parent rankingParent = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaRanking.fxml"));
            Scene rankingScene = new Scene(rankingParent);
            Stage window = new Stage();
            window.setScene(rankingScene);
            window.setTitle("Ranking de Melhores Tempos");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void abrirCreditos(ActionEvent event) {
        try {
            System.out.println("Tentando carregar TelaCreditos.fxml");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/quiz/quiz/TelaCreditos.fxml"));

            CreditosController controller = new CreditosController();
            loader.setController(controller);


            AnchorPane root = new AnchorPane();
            loader.setRoot(root);

            loader.load();

            System.out.println("FXML carregado com sucesso");


            Scene creditosScene = new Scene(root);
            Stage window = new Stage();
            window.setScene(creditosScene);
            window.setTitle("Créditos do Projeto");
            window.show();

            System.out.println("Janela de créditos aberta");
        } catch (IOException e) {
            System.err.println("Erro ao carregar TelaCreditos.fxml: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
            e.printStackTrace();
        }
    }
}