package llvt_group.llvt_project.Registration;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.StageStyle;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;

import java.sql.*;

import llvt_group.llvt_project.AllData.DatabaseConnection ;
import llvt_group.llvt_project.Login.LoginController;

import static llvt_group.llvt_project.Dashboard.DashboardController.switchUI;


public class RegistrationController {
    @FXML private Button registerButton;
    @FXML private Button cancelButton;
    @FXML private Button loginViewButton;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;

    Connection connectDB = DatabaseConnection.getConnection();

    public void userAdd() {
        if (usernameTextField.getText().isEmpty() || passwordPasswordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields!");
            return;
        }

        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        String query = "INSERT INTO users (username, password) " + "VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "User added successfully.");

            Stage currentStage = (Stage) registerButton.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/llvt_group/llvt_project/login-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the user. Please try again.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("LLVT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loginViewButtonOnAction() {
        switchUI(loginViewButton.getScene(), "/llvt_group/llvt_project/login-view.fxml");
    }

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

//        System.exit(0);
    }
}
