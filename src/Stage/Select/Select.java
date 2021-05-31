package Stage.Select;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Select {
    public Select(String[][] selectResult) throws Exception{
        Stage alert = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Select.fxml"));
        Parent root = loader.load();
        SelectController controller = loader.getController();
        controller.setSelectResult(selectResult);

        alert.setTitle("Select Result");
        alert.getIcons().add(new Image("/resources/icon/icon.png"));
        alert.setResizable(false);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setScene(new Scene(root));
        alert.show();
    }
}
