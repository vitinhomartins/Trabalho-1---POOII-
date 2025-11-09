package br.com.quiz.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Carrega a tela de login em vez da tela de exemplo
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TelaLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);  // Ajuste o tamanho conforme necessário (ex.: baseado em TelaLogin)
            stage.setTitle("POO Gênio Quiz - Login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Imprime erro no console para depuração
            System.err.println("Erro ao carregar TelaLogin.fxml: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // Captura qualquer outro erro
            System.err.println("Erro geral na inicialização: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}