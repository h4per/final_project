package llvt_group.llvt_project.Login;

import javafx.scene.Scene;

import javafx.stage.StageStyle;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;
import javafx.scene.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import llvt_group.llvt_project.AllData.CurrentUser;
import llvt_group.llvt_project.AllData.DatabaseConnection;
import llvt_group.llvt_project.AllData.UserData;


public class LoginController {
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordPasswordField;

    Connection connectDB = DatabaseConnection.getConnection();

    public void loginButtonOnAction() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your credentials!");
            return;
        }

        String verifyLogin = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                UserData user = new UserData(queryResult.getInt("id"),
                        queryResult.getString("username"),
                        queryResult.getString("password"));

                CurrentUser.setCurrentUser(user);

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
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error", "An error occurred while accessing the database.");
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