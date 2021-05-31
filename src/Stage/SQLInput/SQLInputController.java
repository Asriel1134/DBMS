package Stage.SQLInput;

import DBMS.SQL;
import DBMS.Test;
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

public class SQLInputController {
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

    public void setChoiceBoxItems(){
        File Databases = new File("src/Database");
        String[] DatabasesList = Databases.list();
        DatabaseChoiceBox.setItems(FXCollections.observableArrayList(DatabasesList));
    }

    public void initChoiceBoxItems(){
        File Databases = new File("src/Database");
        String[] DatabasesList = Databases.list();
        DatabaseChoiceBox.setItems(FXCollections.observableArrayList(DatabasesList));
        DatabaseChoiceBox.getSelectionModel().selectFirst();
    }

    public void submitSQL() throws Exception{
        if (SQLInputTextFiled.getText().equals("")) {
            Alert alert = new Alert("Please enter the SQL statement.");
        }else
            OutputList.add(Test.SQLHandler(SQLInputTextFiled.getText(), DatabaseChoiceBox.getValue()));
            SQLOutputList.setItems(OutputList);
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
