package Stage.SignIn;

import Stage.Alert.Alert;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SignInController {
    public VBox title_Vbox;
    public PasswordField password;
    public TextField username;

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

    public void signIn() throws Exception{
        String un = username.getText();
        String pw = password.getText();
        if(un.equals("") || pw.equals("")){
            Alert alert = new Alert("Please enter the username and password.");
        }
    }
}
