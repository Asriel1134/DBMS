package Stage.Alert;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertController {
    public VBox title_Vbox;
    public Label messageText;


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

    public void setMessageText(String message){
        messageText.setText(message);
    }
}
