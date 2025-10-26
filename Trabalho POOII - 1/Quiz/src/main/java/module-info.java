module br.com.quiz.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens br.com.quiz.quiz to javafx.fxml;  // Já deve ter isso para o HelloApplication
    exports br.com.quiz.quiz;  // Já deve ter isso

    // Adicione esta linha:
    opens br.com.quiz.quiz.Controller to javafx.fxml;
}