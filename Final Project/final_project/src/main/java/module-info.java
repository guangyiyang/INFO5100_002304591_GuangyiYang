module org.example.final_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires metadata.extractor;
    requires javafx.swing;

    exports org.example.final_project;

    opens org.example.final_project to javafx.fxml, javafx.graphics;
    opens org.example.final_project.controllers to javafx.fxml, javafx.graphics;
    opens org.example.final_project.models to javafx.fxml, javafx.graphics;

}