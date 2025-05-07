package llvt_group.llvt_project.Login;

import javafx.scene.Scene;

import javafx.stage.StageStyle;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;
import javafx.scene.Parent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import llvt_group.llvt_project.AllData.DatabaseConnection;


public class LoginController {
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;


    public void loginButtonOnAction() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin = "SELECT * FROM users WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (usernameTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter your credentials!");

            } else {
                if (queryResult.next()) {
                    showAlert(Alert.AlertType.INFORMATION, "Information", "You have successfully logged in!");

                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    currentStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/llvt_group/llvt_project/dashboard-view.fxml"));
                    Parent root = fxmlLoader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Invalid credentials. Try again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//          System.out.println(e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("LLVT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

//        System.exit(0);
    }
}