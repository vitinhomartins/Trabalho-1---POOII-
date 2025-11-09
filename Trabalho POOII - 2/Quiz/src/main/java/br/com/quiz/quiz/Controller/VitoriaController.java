package br.com.quiz.quiz.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class VitoriaController {

    @FXML private Label lblTitulo;
    @FXML private Label lblMensagem;
    @FXML private Button btnVoltarMenu;

    @FXML
    public void voltarParaMenu(ActionEvent event) {
        try {
            Parent menuParent = FXMLLoader.load(getClass().getResource("/br/com/quiz/quiz/TelaMenu.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(menuParent));
            window.setTitle("POO Gênio Quiz - Menu Principal");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar o Menu Principal.");
        }
    }
}