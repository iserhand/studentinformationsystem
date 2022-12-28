package com.oop.informationsystem.GUI;

import com.oop.informationsystem.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LoginController {
    public TextField idField;
    public PasswordField passwordField;
    public Label time;
    public Button loginBtn;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent evt) throws IOException {
        loginBtn.setCursor(Cursor.WAIT);
        Person p2 = login();
        loginBtn.setCursor(Cursor.DEFAULT);
        if (p2 == null) {
            return;
        }
        Stage stage;
        if (idField.getText().equals(p2.getId()) && passwordField.getText().equals(p2.getPassword())) {
            welcomeText.setStyle("-fx-text-fill: green;");
            welcomeText.setText("Log-in success!");
            stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            stage.setUserData(p2);


        } else {
            //Wrong id/password
            onFailure(1);
            return;
        }
        Scene scene;
        if (p2 instanceof Admin) {
            //go to admin panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/admin-view.fxml")));
            scene = new Scene(root);
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();

        } else if (p2 instanceof Student) {
            //go to student panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/student-view.fxml")));
            scene = new Scene(root);
            stage.setTitle("Student");
            stage.setScene(scene);
            stage.show();
        } else if (p2 instanceof Professor) {
            //go to professor panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/professor-view.fxml")));
            scene = new Scene(root);
            stage.setTitle("Professor");
            stage.setScene(scene);
            stage.show();
        }


    }


    private void onFailure(int reason) {
        switch (reason) {
            case 0 -> {
                //User not found
                welcomeText.setStyle("-fx-text-fill: red;");
                welcomeText.setText("user not found!");
            }
            case 1 -> {
                //Incorrect username or password
                welcomeText.setStyle("-fx-text-fill: red;");
                welcomeText.setText("incorrect username/password");
            }
        }
    }

    @FXML
    public void initialize() {
        //Start the timer for time and date at initialisation

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            }
        };
        timer.start();
    }

    private Person login() {
        Person p2 = null;
        try {
            FileInputStream fileInputStream
                    = new FileInputStream("database/users/" + idField.getText() + ".txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            p2 = (Person) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            onFailure(0);
        }
        return p2;
    }
}