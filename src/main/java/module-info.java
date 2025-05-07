module llvt_group.llvt_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires java.desktop;

    opens llvt_group.llvt_project to javafx.fxml;
    exports llvt_group.llvt_project;

    exports llvt_group.llvt_project.Registration;
    opens llvt_group.llvt_project.Registration to javafx.fxml;

    exports llvt_group.llvt_project.Login;
    opens llvt_group.llvt_project.Login to javafx.fxml;

    exports llvt_group.llvt_project.Dashboard;
    opens llvt_group.llvt_project.Dashboard to javafx.fxml;

    exports llvt_group.llvt_project.AllData;
    opens llvt_group.llvt_project.AllData to javafx.fxml;

}