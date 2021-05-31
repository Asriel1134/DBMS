package Stage.Select;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Arrays;

public class SelectController {
    public VBox title_Vbox;
    public TableView<String[]> selectResultTable;


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

    public void setSelectResult(String[][] selectResult){
        String[][] selectResultData = new String[selectResult.length-1][selectResult[0].length];
        System.arraycopy(selectResult, 1, selectResultData, 0, selectResult.length - 1);    //去掉第一行

        ObservableList<String[]> selectResultList = FXCollections.observableArrayList();
        selectResultList.addAll(Arrays.asList(selectResultData));
        selectResultTable.setItems(selectResultList);

        for (int i=0; i<selectResultData[0].length; i++) {
            TableColumn<String[], String> column = new TableColumn<>(selectResult[0][i]);
            int finalI = i;
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> stringCellDataFeatures) {
                    return new SimpleStringProperty(stringCellDataFeatures.getValue()[finalI]);
                }
            });
            selectResultTable.getColumns().add(column);
        }
    }
}
