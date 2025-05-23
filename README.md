# LLVT Vocabulary Tracker  
‿︵‿ヽ(°□° )ノ︵‿︵  

---
# Presentation link ---> 
### https://www.canva.com/design/DAGlVzn-QQk/sy9hEtH8keoSzGZxue9J1w/edit?utm_content=DAGlVzn-QQk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

---

# Description
## This is a JavaFX-based application for managing vocabulary when learning a new language.  You can add words, write definitions, provide usage examples, and organize them by language. 

---

# Features
1. **Add new words** with definitions and examples  
2. **Choose a language**: English, German, Russian 
3. **Avoid duplicates** – app checks before saving  
4. **Update existing words** easily  
5. **Switch tabs** between Learn, Profile  
6. **Clean and responsive UI** with JavaFX TableView  
7. **SQLite database** — fast, light, local   
8. **Fully offline and private** — your vocab is yours
    
---

# Data Structures & Models
---
### 1) `DashboardController` class
    Represents the landing page and logic of switching between tabs in the application.
    
### 2) `LoginController` class
    Represents the login page and logic of user validation.
    
### 3) `RegistartionController` class
    Represents the registration page and logic of adding user into batabase(registration).
    
### 4) `LearnTabController` class
   Represents the main page of the application and holds metadata like ID, word, definition and etc.
   
   - `ObservableList<VocabularyData>`: stores the data of the words
   - CRUD operations interact with SQLite database.
     
---

### SQLite Schema
    CREATE TABLE vocabulary (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    word TEXT NOT NULL,
    definition TEXT NOT NULL,
    example_sentence TEXT NOT NULL,
    language_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    is_learned BOOLEAN DEFAULT 0,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (language_id) REFERENCES languages(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );
    
    CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
    );
    
    CREATE TABLE languages (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE
    );
---

# Installation
## Prerequisites:
- Java 17+ 
- JavaFX 23  
- SQLite JDBC driver (already included)
- SceneBuilder
- DB Browser for SQLite
---

# Setup:
1. Clone or download the project  
2. Set the path to your database in `DatabaseConnection.java`:
   ```java
   String url = "jdbc:sqlite:D:\\db_LVVT_project\\userAccounts.db";
   
---

# How to use
## 1) Compile and run 
```sh
javac -cp .;path\to\javafx\lib\* llvt_group\llvt_project\App.java
java -cp .;path\to\javafx\lib\* llvt_group.llvt_project.App
```
Replace path\to\javafx\lib\* with your JavaFX SDK path

---

## 2) Start the app
  - Choose a tab (Learn / Practice / Profile)
  - Add new words by filling the text fields
  - Choose a language from the dropdown
  - Click the "Add" button
  - The word is added
    
---
