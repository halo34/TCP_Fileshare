package ClientServer.view;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class FileSyncApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static final String FXML_FILE_NAME = "FileSyncApplicaation.fxml";
    private static final String ICON_PATH_OF_APPLICATION = "resources/Icon_Fileshare.png";
    @Override
    public void start(Stage primaryStage) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation();
        Controller.setScene(primaryStage);
        Parent page = null;
        try {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(FXML_FILE_NAME)));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Auswahl des Starttyps");
            InputStream inputStream = Files.newInputStream(Path.of(ICON_PATH_OF_APPLICATION));
            Image image = new Image(inputStream);
            primaryStage.getIcons().add(image);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        JPanel jPanel = new JPanel();
//        //calling from a different controller and don't have the scene object loaded.
//        JOptionPane.showMessageDialog(jPanel,"Hello","This", JOptionPane.ERROR_MESSAGE);
//        stage.close();
    }

    @FXML
    public void cancel(ActionEvent event) {
        System.out.println("geklickt");
        Platform.exit();
    }

    @FXML
    public void client(ActionEvent event) {
        Controller.folderChooser();
    }

    @FXML
    public void server(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
        Controller.filechoose();
    }
}
