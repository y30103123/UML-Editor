package com.editor;


import com.editor.services.Constants;
import com.editor.view.OptionCell;
import javafx.geometry.Point2D;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements Initializable {

    @FXML
    private Canvas editCanvas;
    @FXML
    private ListView<String> optionList;
    @FXML
    private MenuItem group;
    @FXML
    private MenuItem unGroup;
    @FXML
    private MenuItem changeName;

    private EditorController editorController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize  EditorController
        editorController = EditorController.getInstance();
        editorController.setCanvas(editCanvas);
        editorController.initCanvasListener();

        // Initialize listView of left side
        optionList.setItems(FXCollections.observableArrayList( Constants.TYPE_VALUES));
        optionList.setCellFactory(param -> {
            final ListCell cell = new OptionCell();
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty() && cell.getIndex() != 0) {
                    Constants.TYPE[] values = Constants.TYPE.values();
                    editorController.setMode(values[cell.getIndex()]);
                }
                editorController.unSelectAll();
            });
            return cell;
        });

        group.setOnAction(e -> editorController.group());
        unGroup.setOnAction(e -> editorController.unGroup());
        changeName.setOnAction(e -> editorController.changeName());
    }
}
