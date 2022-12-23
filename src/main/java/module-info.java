module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.oop.informationsystem to javafx.fxml;
    exports com.oop.informationsystem;
    exports com.oop.informationsystem.GUI;
    opens com.oop.informationsystem.GUI to javafx.fxml;
}