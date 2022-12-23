package com.oop.informationsystem.GUI;

import com.oop.informationsystem.Admin;
import com.oop.informationsystem.Person;
import com.oop.informationsystem.Student;
import com.oop.informationsystem.Faculty;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LoginController {
    public TextField idField;
    public PasswordField passwordField;
    public Label time;
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onHelloButtonClick(ActionEvent evt) {
        Person p2 = null;
        try {
            FileInputStream fileInputStream
                    = new FileInputStream("database/" + idField.getText() + ".txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            p2 = (Person) objectInputStream.readObject();
            objectInputStream.close();
            if (p2 instanceof Admin) {
                //go to admin panel
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/admin-view.fxml")));
                stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                stage.getIcons().add(new Image("https://img.icons8.com/ios-glyphs/30/null/student-center.png"));
                scene = new Scene(root);
                stage.setTitle("Admin");
                stage.setScene(scene);
                stage.show();

            } else if (p2 instanceof Student) {

            } else if (p2 instanceof Faculty) {

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            System.out.println("Sıkıntı");
        }
        if (idField.getText().equals(p2.getId()) && passwordField.getText().equals(p2.getPassword())) {
            welcomeText.setStyle("-fx-text-fill: green;");
            welcomeText.setText(p2.getClass().toString());
        } else {
            welcomeText.setStyle("-fx-text-fill: red;");
            welcomeText.setText("incorrect");
        }
    }


    private void onFailure(int reason) {
        switch (reason) {
            case 0:
                //User not found
                break;
            case 1:
                //Incorrect username or password
                break;
        }
    }

    @FXML
    public void initialize() {
        //Start the timer for time and date at initialisation
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();
    }
}