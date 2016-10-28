package com.editor.mode;


import com.editor.EditorController;
import com.editor.component.BasicObject;
import com.editor.component.Component;
import com.editor.component.ComponentFactory;
import javafx.scene.input.MouseEvent;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by y3010 on 2016/10/25.
 * <p>
 * This  abstract class use to change state of mouse action.
 * The state will change by clicking list View from left side.
 */
public abstract class Mode {

    // These variables will get value from instance of EditorController .
    protected List<Component> componentList = EditorController.getInstance().getAllComponents();
    protected List<BasicObject> basicObjects = EditorController.getInstance().getAlBasicObjects();
    protected ComponentFactory factory = ComponentFactory.getInstance();

    public void onMouseDragged(MouseEvent e) {
    }

    public void onMousePressed(MouseEvent e) {
    }

    public void onMouseReleased(MouseEvent e) {
    }

    public void unGroup() {
    }

    public void group() {
    }

    public void changeObjectName() {
    }

    protected void draw() {
        EditorController.getInstance().draw();
    }

    /**
     * Get the  selected object of top priority which is degree of depth.
     *
     * @param basicObjects basicObjects from canvas
     * @param x            mouse action coordinate of x
     * @param y            mouse action coordinate of y
     * @return result value
     */
    protected Optional<BasicObject> getSelectedFirstObject(List<BasicObject> basicObjects, double x, double y) {
        Comparator<BasicObject> compareDepth = (c1, c2) ->
                Integer.compare(c1.getDepth(), c2.getDepth());
        return basicObjects.stream()
                .filter(c -> c.isContain(x, y))
                .sorted(compareDepth)
                .findFirst();
    }

    /**
     * get all selected objects.
     *
     * @param basicObjects basicObjects from canvas
     * @return return result value
     */
    protected List<BasicObject> getSelectedbasicObjects(List<BasicObject> basicObjects) {
        return basicObjects.stream()
                .filter(Component::isSelected)
                .collect(Collectors.toList());
    }
}
