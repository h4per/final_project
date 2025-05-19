package llvt_group.llvt_project.AllData;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection databaseLink;
//    public final Logger logger = Logger.getLogger(this.getClass().getName());

    public static Connection getConnection() {
        String databaseURL = "jdbc:sqlite:D:\\db_LVVT_project\\LLVT_db.db";

        try{
            if(databaseLink == null || databaseLink.isClosed()) {
                databaseLink = DriverManager.getConnection(databaseURL);
                System.out.println("Connected to database successfully.");
//                logger.info("Connected to database!");
            }
        }
         catch (Exception e){
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }

        return databaseLink;
    }
}
