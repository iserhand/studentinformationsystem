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
    public Button loginBtn;
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onHelloButtonClick(ActionEvent evt) throws IOException {
        Person p2 = login(evt);
        loginBtn.setCursor(Cursor.WAIT);
        if (p2 == null) {
            loginBtn.setCursor(Cursor.DEFAULT);
            return;
        }
        if (idField.getText().equals(p2.getId()) && !passwordField.getText().equals(p2.getPassword())
                || !idField.getText().equals(p2.getId()) && !passwordField.getText().equals(p2.getPassword())
                || !idField.getText().equals(p2.getId()) && passwordField.getText().equals(p2.getPassword())) {
            //Wrong id/password
            onFailure(1);

        } else {
            welcomeText.setStyle("-fx-text-fill: green;");
            welcomeText.setText("Log-in success!");
        }
        if (p2 instanceof Admin) {
            //go to admin panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/admin-view.fxml")));
            stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();

        } else if (p2 instanceof Student) {
            //go to student panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/student-view.fxml")));
            stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Student");
            stage.setScene(scene);
            stage.show();
        } else if (p2 instanceof Faculty) {
            //go to faculty panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/admin-view.fxml")));
            stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Faculty");
            stage.setScene(scene);
            stage.show();
        } else if (p2 instanceof Professor) {
            //go to professor panel
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/professor-view.fxml")));
            stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Professor");
            stage.setScene(scene);
            stage.show();
        }
        loginBtn.setCursor(Cursor.DEFAULT);

    }


    private void onFailure(int reason) {
        switch (reason) {
            case 0:
                //User not found
                welcomeText.setStyle("-fx-text-fill: red;");
                welcomeText.setText("user not found!");
                break;
            case 1:
                //Incorrect username or password
                welcomeText.setStyle("-fx-text-fill: red;");
                welcomeText.setText("incorrect username/password");
                break;
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

    private Person login(ActionEvent evt) {
        Person p2 = null;
        try {
            FileInputStream fileInputStream
                    = new FileInputStream("database/users/" + idField.getText() + ".txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            p2 = (Person) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            onFailure(0);
        } catch (IOException ex) {
            onFailure(0);
        }
        return p2;
    }
}