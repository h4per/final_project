package llvt_group.llvt_project.Dashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import llvt_group.llvt_project.AllData.DatabaseConnection;
import llvt_group.llvt_project.AllData.GetData;
import llvt_group.llvt_project.AllData.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static llvt_group.llvt_project.Dashboard.DashboardController.switchUI;

public class ProfileTabController implements Initializable {

    @FXML public Button learnActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public Button logoutButton;
    @FXML public Button updateButton;
    @FXML public Button deleteButton;

    @FXML public MenuButton languageChooseBox;

    @FXML public Label usernameLabel;
    @FXML public Label wordsLearnedLabel;

    @FXML
    public void learnActionButtonClicked() {
        switchUI(learnActionButton.getScene(), "/llvt_group/llvt_project/learntab-view.fxml");
    }

    @FXML
    public void profileActionButtonClicked() {
        switchUI(profileActionButton.getScene(), "/llvt_group/llvt_project/profiletab-view.fxml");
    }

    @FXML
    public void logoutButtonClicked() {
        switchUI(logoutButton.getScene(), "/llvt_group/llvt_project/login-view.fxml");
    }

    @FXML
    public void exitButtonOnAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void displayUsername() {
        String username = GetData.username;
        usernameLabel.setText(username);
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("LLVT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
//        updateAndDisplayLearnedWords();
    }
}
