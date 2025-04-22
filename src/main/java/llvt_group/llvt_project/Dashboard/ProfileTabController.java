package llvt_group.llvt_project.Dashboard;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import llvt_group.llvt_project.AllData.GetData;
import static llvt_group.llvt_project.Dashboard.DashboardController.switchUI;

public class ProfileTabController implements Initializable {
    @FXML public Button learnActionButton;
    @FXML public Button practiceActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public Button logoutButton;
    @FXML public Button updateButton;
    @FXML public Button deleteButton;

    @FXML public MenuButton languageChooseBox;

    @FXML public Label usernameLabel;

    public void learnActionButtonClicked() {
        switchUI(learnActionButton.getScene(), "/llvt_group/llvt_project/learntab-view.fxml");
    }
    public void practiceActionButtonClicked() {
        switchUI(practiceActionButton.getScene(), "/llvt_group/llvt_project/practicetab-view.fxml");
    }
    public void profileActionButtonClicked() {
        switchUI(profileActionButton.getScene(), "/llvt_group/llvt_project/profiletab-view.fxml");
    }

    public void exitButtonOnAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

//        System.exit(0);
    }

    public void displayUsername(){
        String user = GetData.username;
        usernameLabel.setText(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
    }
}
