package llvt_group.llvt_project.Dashboard;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;

import static llvt_group.llvt_project.Dashboard.DashboardController.*;


public class PracticeTabController{
    @FXML public TextArea practiceDefinitionArea;
    @FXML public TextField answerField;

    @FXML public Button checkAnswerButton;
    @FXML public Button nextQuestionButton;

    @FXML public Button learnActionButton;
    @FXML public Button practiceActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public MenuButton languageChooseBox;

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
