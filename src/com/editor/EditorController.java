package com.editor;

import com.editor.component.BasicObject;
import com.editor.component.Component;

import com.editor.mode.Mode;
import com.editor.services.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by y3010 on 2016/10/24.
 */
public class EditorController {

    private Canvas editCanvas;
    private List<Component> components;
    private Mode currentMode;
    private Constants.TYPE currentType;
    private static EditorController controller;

    public EditorController() {
        this.components = new ArrayList<>();
        this.currentMode = Constants.MODE_MAP.get(Constants.TYPE.SELECT);
    }

    public static EditorController getInstance() {
        if (controller == null) {
            controller = new EditorController();
        }
        return controller;
    }

    public void setCanvas(Canvas canvas) {
        this.editCanvas = canvas;
    }

    public void setMode(Constants.TYPE type) {
        this.currentType = type;
        this.currentMode = Constants.MODE_MAP.get(type);
    }

    public void initCanvasListener() {
        editCanvas.setOnMouseReleased(e -> currentMode.onMouseReleased(e));
        editCanvas.setOnMousePressed(e -> currentMode.onMousePressed(e));
        editCanvas.setOnMouseDragged(e -> currentMode.onMouseDragged(e));
    }

    public Constants.TYPE getCurrentType() {
        return currentType;
    }

    public List<BasicObject> getAlBasicObjects() {
        return components.stream()
                .filter(c -> c instanceof BasicObject)
                .map(BasicObject.class::cast)
                .collect(Collectors.toList());
    }

    public List<Component> getAllComponents() {
        return components;
    }

    public void unSelectAll() {
        components.forEach(Component::unSelect);
    }


    /**
     * Draw  all components .
     */
    public void draw() {
        GraphicsContext g = editCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, editCanvas.getWidth(), editCanvas.getHeight());
        components.forEach(c -> c.draw(g));
    }

    // region mode action
    public void group() {
        currentMode.group();
    }

    public void unGroup() {
        currentMode.unGroup();
    }

    public void changeName() {
        currentMode.changeObjectName();
    }
    //endregion
}
