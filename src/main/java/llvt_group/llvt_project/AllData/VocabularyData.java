package llvt_group.llvt_project.AllData;

import java.sql.Date;

public class VocabularyData {
    private Integer id;
    private String word;
    private String definition;
    private Integer languageId;
    private String exampleSentence;
    private boolean isLearned;
    private Integer userId;
    private String createdAt;

    public VocabularyData(Integer id, String word, String definition, Integer languageId,
                          String exampleSentence, boolean isLearned, Integer userId, String createdAt) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.languageId = languageId;
        this.exampleSentence = exampleSentence;
        this.isLearned = isLearned;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public void setLearned(boolean learned) {
        isLearned = learned;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return id +")" + " " +  word + " " + definition + " - " + exampleSentence;
    }
}
