package llvt_group.llvt_project.Dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static llvt_group.llvt_project.Dashboard.DashboardController.*;
import llvt_group.llvt_project.AllData.DatabaseConnection;
import llvt_group.llvt_project.AllData.VocabularyData;

import java.net.URL;
import java.sql.*;
import java.util.Date;
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
    @FXML public Button addDefinitionButton;
    @FXML public Button addExampleButton;

    @FXML public Button learnActionButton;
    @FXML public Button practiceActionButton;
    @FXML public Button profileActionButton;
    @FXML public Button exitButton;

    @FXML public MenuButton languageChooseBox;

    public void vocabularyUpdate() {
        VocabularyData selectedItem = vocabularyTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a word from the table to update.");
            return;
        }

        if (wordTextField.getText().isEmpty() || definitionTextArea.getText().isEmpty() || exampleTextArea.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("LLVT");
        alert.setContentText("Are you sure you want to update this vocabulary?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            Connection connectDB = DatabaseConnection.getConnection();
            String query = "UPDATE vocabulary SET word = ?, definition = ?, example_sentence = ?, language_id = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connectDB.prepareStatement(query)) {

                int languageId = switch (selectedLanguage.toUpperCase()) {
                    case "ENGLISH" -> 1;
                    case "GERMAN" -> 2;
                    case "RUSSIAN" -> 3;
                    default -> 0;
                };

                preparedStatement.setString(1, wordTextField.getText());
                preparedStatement.setString(2, definitionTextArea.getText());
                preparedStatement.setString(3, exampleTextArea.getText());
                preparedStatement.setInt(4, languageId);
                preparedStatement.setInt(5, selectedItem.getId());

                preparedStatement.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Vocabulary updated successfully!");

                setAddVocabularyListShowData();
                vocabularyReset();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void vocabularyAdd(){
        Connection connectDB = DatabaseConnection.getConnection();
        String query = "INSERT INTO vocabulary (word, definition, example_sentence, language_id, created_at) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int languageId = switch (selectedLanguage.toUpperCase()) {
            case "ENGLISH" -> 1;
            case "GERMAN" -> 2;
            case "RUSSIAN" -> 3;
            default -> 0;
        };

        try{
            if (wordTextField.getText().isEmpty() || definitionTextArea.getText().isEmpty() || exampleTextArea.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields!");
            }else{
                String checkData = "SELECT word FROM vocabulary WHERE word = '"+wordTextField.getText()+"'";
                preparedStatement = connectDB.prepareStatement(checkData);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    showAlert(Alert.AlertType.ERROR, "Error", "This word already exists!");

                }else{
                    preparedStatement = connectDB.prepareStatement(query);
                    preparedStatement.setString(1, wordTextField.getText());
                    preparedStatement.setString(2, definitionTextArea.getText());
                    preparedStatement.setString(3, exampleTextArea.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    preparedStatement.setInt(4, languageId);
                    preparedStatement.setString(5, String.valueOf(sqlDate));

                    preparedStatement.executeUpdate();
                    showAlert(Alert.AlertType.INFORMATION, "Information", "New vocabulary successfully added!");

                    setAddVocabularyListShowData();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vocabularyReset(){
        wordTextField.setText("");
        definitionTextArea.setText("");
        exampleTextArea.setText("");
    }

    public void vocabularySelect(){
        VocabularyData vocabData = vocabularyTable.getSelectionModel().getSelectedItem();
        int num =  vocabularyTable.getSelectionModel().getSelectedIndex();

        if(num - 1 < - 1){
            return;
        }
        wordTextField.setText(vocabData.getWord());
        definitionTextArea.setText(vocabData.getDefinition());
        exampleTextArea.setText(vocabData.getExampleSentence());
    }


    public ObservableList<VocabularyData> addVocabularyData() {
        ObservableList<VocabularyData> listData = FXCollections.observableArrayList();

        Connection connectDB = DatabaseConnection.getConnection();
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
        }

        return listData;
    }

    private ObservableList<VocabularyData> addVocabularyList;

    public void setAddVocabularyListShowData(){
        addVocabularyList = addVocabularyData();

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        exampleColumn.setCellValueFactory(new PropertyValueFactory<>("exampleSentence"));

        vocabularyTable.setItems(addVocabularyList);
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
            selectedLanguage = "GERMAN";
            languageChooseBox.setText("GERMAN");
        });

        russianMenuItem.setOnAction(e -> {
            selectedLanguage = "RUSSIAN";
            languageChooseBox.setText("RUSSIAN");
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectLanguage();
        setAddVocabularyListShowData();
        vocabularyReset();
    }
}
