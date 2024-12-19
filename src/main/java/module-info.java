module tech.xoslu.etudiantsjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires TrayNotification;


    opens tech.xoslu.etudiantsjavafx to javafx.fxml;
    exports tech.xoslu.etudiantsjavafx;
    opens tech.xoslu.etudiantsjavafx.controller to javafx.fxml;
    exports tech.xoslu.etudiantsjavafx.controller;
    exports tech.xoslu.etudiantsjavafx.entity;
    opens tech.xoslu.etudiantsjavafx.entity to javafx.fxml;
    exports tech.xoslu.etudiantsjavafx.db;
    opens tech.xoslu.etudiantsjavafx.db to javafx.fxml;
}