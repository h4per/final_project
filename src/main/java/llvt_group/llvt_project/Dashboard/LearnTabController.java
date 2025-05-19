package llvt_group.llvt_project.Dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import static llvt_group.llvt_project.Dashboard.DashboardController.*;
import llvt_group.llvt_project.Dashboard.ProfileTabController.*;
import llvt_group.llvt_project.AllData.DatabaseConnection;
import llvt_group.llvt_project.AllData.VocabularyData;

import java.net.URL;

import java.sql.*;

import java.util.Optional;
import java.util.ResourceBundle;


public class LearnTabController implements Initializable {
    @FXML public TextField wordTextField;
    @FXML public TextArea definitionTextArea;
    @FXML public TextArea exampleTextArea;

    @FXML public TableView<VocabularyData> vocabularyTable;
    @FXML private TableColumn<VocabularyData, String> wordColumn;
    @FXML private TableColumn<VocabularyData, String> definitionColumn;
    @FXML private TableColumn<VocabularyData, String> exampleColumn;

    @FXML public MenuItem englishMenuItem;
    @FXML public MenuItem germanMenuItem;
    @FXML public MenuItem russianMenuItem;

    private String selectedLanguage = "RUSSIAN";

    @FXML public Button addWordButton;
    @FXML public Button updateWordButton;
    @FXML public Button deleteWordButton;

    @FXML public Button learnActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public MenuButton languageChooseBox;

    Connection connectDB = DatabaseConnection.getConnection();

    public void vocabularyAdd() {
        int languageId = switch (selectedLanguage.toUpperCase()) {
            case "ENGLISH" -> 1;
            case "RUSSIAN" -> 2;
            case "GERMAN" -> 3;
            default -> 0;
        };

        if (wordTextField.getText().isEmpty() || definitionTextArea.getText().isEmpty() || exampleTextArea.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields!");
            return;
        }

        String word = wordTextField.getText();
        String definition = definitionTextArea.getText();
        String example = exampleTextArea.getText();

        String query = "INSERT INTO vocabulary (word, definition, example_sentence, language_id, user_id, is_learned, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);

            preparedStatement.setString(1, word);
            preparedStatement.setString(2, definition);
            preparedStatement.setString(3, example);
            preparedStatement.setInt(4, languageId);
            preparedStatement.setInt(5, 0);
            preparedStatement.setBoolean(6, true);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Word added successfully.");
                vocabularyReset();
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());

            showAlert(Alert.AlertType.ERROR, "Database error", e.getMessage());
        }
    }

    public void vocabularyUpdate() {
        VocabularyData selectedItem = vocabularyTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a word from the table to update!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("LLVT");
        alert.setContentText("Are you sure you want to update this wordset?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            String query = "UPDATE vocabulary SET word = ?, definition = ?, example_sentence = ?, language_id = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {

                int languageId = switch (selectedLanguage.toUpperCase()) {
                    case "ENGLISH" -> 1;
                    case "RUSSIAN" -> 2;
                    case "GERMAN" -> 3;
                    default -> 0;
                };

                preparedStatement.setString(1, wordTextField.getText());
                preparedStatement.setString(2, definitionTextArea.getText());
                preparedStatement.setString(3, exampleTextArea.getText());
                preparedStatement.setInt(4, languageId);
                preparedStatement.setInt(5, selectedItem.getId());

                preparedStatement.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Wordset updated successfully!");

                vocabularyListShowData();
                vocabularyReset();

            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println(e.getMessage());
            }
        }
    }

    public void vocabularyDelete() {
        VocabularyData selectedItem = vocabularyTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a word from the table to delete!");
            return;
        }

        String query = "DELETE FROM vocabulary WHERE id = ?";

        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setInt(1, selectedItem.getId());
            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Word deleted successfully!");

            vocabularyListShowData();

        } catch(Exception e){
            e.printStackTrace();
//            System.out.println(e.getMessage());
        }
    }

    public ObservableList<VocabularyData> getVocabulary() {
        ObservableList<VocabularyData> listData = FXCollections.observableArrayList();

        String query = "SELECT * FROM vocabulary";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try{
            preparedStatement = connectDB.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            VocabularyData vocabData;
            while(resultSet.next()){
                vocabData = new VocabularyData(resultSet.getInt("id"),
                        resultSet.getString("word"), resultSet.getString("definition"),
                        resultSet.getInt("language_id"), resultSet.getString("example_sentence"), resultSet.getBoolean("is_learned"),
                        resultSet.getInt("user_id"), resultSet.getString("created_at"));

                listData.add(vocabData);
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
        }

        return listData;
    }

    public void vocabularyListShowData(){
        ObservableList<VocabularyData> addVocabularyList = getVocabulary();

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        exampleColumn.setCellValueFactory(new PropertyValueFactory<>("exampleSentence"));

        vocabularyTable.setItems(addVocabularyList);
    }

    public void vocabularyReset(){
        wordTextField.setText("");
        definitionTextArea.setText("");
        exampleTextArea.setText("");
    }

    public void vocabularySelect(){
        VocabularyData vocabData = vocabularyTable.getSelectionModel().getSelectedItem();

        if (vocabData == null){
            return;
        }

        wordTextField.setText(vocabData.getWord());
        definitionTextArea.setText(vocabData.getDefinition());
        exampleTextArea.setText(vocabData.getExampleSentence());
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText("LLVT");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void selectLanguage(){
        englishMenuItem.setOnAction(e -> {
            selectedLanguage = "ENGLISH";
            languageChooseBox.setText("ENGLISH");
        });

        germanMenuItem.setOnAction(e -> {
            selectedLanguage = "RUSSIAN";
            languageChooseBox.setText("RUSSIAN");
        });

        russianMenuItem.setOnAction(e -> {
            selectedLanguage = "GERMAN";
            languageChooseBox.setText("GERMAN");
        });
    }

    @FXML public void learnActionButtonClicked() {
        switchUI(learnActionButton.getScene(), "/llvt_group/llvt_project/learntab-view.fxml");
    }

    @FXML public void profileActionButtonClicked() {
        switchUI(profileActionButton.getScene(), "/llvt_group/llvt_project/profiletab-view.fxml");
    }

    @FXML public void exitButtonOnAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectLanguage();
        vocabularySelect();
        vocabularyReset();
    }
}