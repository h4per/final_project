package llvt_group.llvt_project.Dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.stage.Stage;

import llvt_group.llvt_project.AllData.CurrentUser;
import llvt_group.llvt_project.AllData.DatabaseConnection;
import llvt_group.llvt_project.AllData.UserData;

import static llvt_group.llvt_project.Dashboard.DashboardController.switchUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;


public class ProfileTabController implements Initializable {
    @FXML public Button learnActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public Button logoutButton;
    @FXML public Button userDeleteButton;

    @FXML public MenuButton languageChooseBox;

    @FXML public Label usernameLabel;
    @FXML public Label updateAndDisplayLearnedWords;

    Connection connectDB = DatabaseConnection.getConnection();
    UserData currentUser;

    public ObservableList<UserData> getUsers() {
        ObservableList<UserData> listData = FXCollections.observableArrayList();

        String query = "SELECT * FROM users";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connectDB.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            UserData userData;

            while (resultSet.next()) {
                userData = new UserData(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));

                listData.add(userData);
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        }
        return listData;
    }


    public void userDelete() {
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            int user_id = currentUser.getId();

            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully!");

            switchUI(userDeleteButton.getScene(), "/llvt_group/llvt_project/login-view.fxml");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error", e.getMessage());
        }
    }


    public void displayUsername(){
        UserData currentUser = CurrentUser.getCurrentUser();

        if (currentUser != null) {
            usernameLabel.setText(currentUser.getUsername());
        } else {
            usernameLabel.setText("No user logged in");
        }
    }


    public void displayVocabulary() {
        String query = "SELECT COUNT(is_learned) FROM vocabulary";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Learned words: " + count);
                updateAndDisplayLearnedWords.setText(Integer.toString(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Database error", e.getMessage());
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("LLVT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML public void learnActionButtonClicked() {
        switchUI(learnActionButton.getScene(), "/llvt_group/llvt_project/learntab-view.fxml");
    }

    @FXML public void profileActionButtonClicked() {
        switchUI(profileActionButton.getScene(), "/llvt_group/llvt_project/profiletab-view.fxml");
    }

    @FXML public void logoutButtonClicked() {
        switchUI(logoutButton.getScene(), "/llvt_group/llvt_project/login-view.fxml");
    }

    @FXML public void exitButtonOnAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        displayVocabulary();
        getUsers();
    }
}
