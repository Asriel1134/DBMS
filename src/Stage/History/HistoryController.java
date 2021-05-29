package Stage.History;

import DBMS.SQL;
import Stage.Alert.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class HistoryController {
    public VBox title_Vbox;
    public TextField SQLInputTextFiled;
    public ListView<String> SQLOutputList;
    public ChoiceBox<String> DatabaseChoiceBox;
    ObservableList<String> OutputList = FXCollections.observableArrayList();



    public void winClose(){
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        stage.close();
    }

    public void winMinimize(){
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        stage.setIconified(true);
    }

    private double xOffset;
    private double yOffset;

    public void setDragXY(){
        Scene scene = title_Vbox.getScene();
        scene.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
    }

    public void dragWindow(){
        Scene scene = title_Vbox.getScene();
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        scene.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
    }

    public void changeAbout() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stage/About/About.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changeHistory() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stage/History/History.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void changeSQL() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stage/SQLInput/SQLInput.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) title_Vbox.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
