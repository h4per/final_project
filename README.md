# LLVT Vocabulary Tracker  
‿︵‿ヽ(°□° )ノ︵‿︵  

---
### Presentation link ---> 
## https://www.canva.com/design/DAGlVzn-QQk/sy9hEtH8keoSzGZxue9J1w/edit?utm_content=DAGlVzn-QQk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

### Description
## This is a JavaFX-based application for managing vocabulary when learning a new language.  You can add words, write definitions, provide usage examples, and organize them by language. 
---

### Features
1. **Add new words** with definitions and examples  
2. **Choose a language**: English, German, Russian 
3. **Avoid duplicates** – app checks before saving  
4. **Update existing words** easily  
5. **Switch tabs** between Learn, Profile  
6. **Clean and responsive UI** with JavaFX TableView  
7. **SQLite database** — fast, light, local  
8. **Auto-fill fields** when selecting a word   
10. **Fully offline and private** — your vocab is yours
---

### Data Structures & Models
---
1) *DashboardController* class
Represents the landing page and logic of switching between tabs in the application.




## Installation
### Prerequisites:
- Java 17+ 
- JavaFX 23  
- SQLite JDBC driver (already included)
- SceneBuilder
- DB Browser for SQLite
---

### Setup:
1. Clone or download the project  
2. Set the path to your database in `DatabaseConnection.java`:
   ```java
   String url = "jdbc:sqlite:D:\\db_LVVT_project\\userAccounts.db";
---

## How to use
### 1) Compile and run 
```sh
javac -cp .;path\to\javafx\lib\* llvt_group\llvt_project\App.java
java -cp .;path\to\javafx\lib\* llvt_group.llvt_project.App
```
Replace path\to\javafx\lib\* with your JavaFX SDK path

---

### 2) Start the app
  - Choose a tab (Learn / Practice / Profile)
  - Add new words by filling the text fields
  - Choose a language from the dropdown
  - Click the "Add" button
  - The word is added
---
