package llvt_group.llvt_project.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardController {
    @FXML public Button learnActionButton;
    @FXML public Button practiceActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public MenuButton languageChooseBox;

    public static void switchUI(Scene currentScene, String fxml) {
        Stage currentStage = (Stage) currentScene.getWindow();
        currentStage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DashboardController.class.getResource(fxml));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
