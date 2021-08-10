package ClientServer.view;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class Controller {
    public static Stage getScene() {
        return scene;
    }

    public Controller(Stage scene) {
        Controller.scene = scene;
    }

    public static void setScene(Stage scene) {
        Controller.scene = scene;
    }

    private static Stage scene;
    private static final Path DESKTOP = Path.of(System.getProperty("user.home") + "/Desktop");
    static Path filechoose() throws NoSuchElementException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File to send");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files","*.*"));
        File selectedFile = fileChooser.showOpenDialog(scene);
        if (selectedFile!= null) {
            return selectedFile.toPath();
        } else {
            throw new NoSuchElementException();
        }
    }
    static Path folderChooser() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(DESKTOP.toFile());
        final File selectedDirectory = directoryChooser.showDialog(scene);
        if (selectedDirectory!= null) {
            return selectedDirectory.toPath();
        } else {
            return DESKTOP;
        }
    }
}
