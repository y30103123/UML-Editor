package com.editor.mode;

import com.editor.EditorController;
import com.editor.component.BasicObject;
import com.editor.component.Component;
import com.editor.component.Composite;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Optional;


/**
 * Created by fish on 2016/10/25.
 */
public class SelectMode extends Mode {

    private double startX;
    private double startY;
    private boolean isEmpty;
    private static Mode instance;

    public static Mode getInstance(){
        if(instance==null){
            instance = new SelectMode();
        }
        return instance;
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        basicObjects.forEach(c -> {
            if (c.isSelected()) {
                c.moveTo(x, y);
            }
        });
        this.draw();
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        basicObjects = EditorController.getInstance().getAlBasicObjects();
        EditorController.getInstance().unSelectAll();
        double x = e.getX();
        double y = e.getY();

        Optional<BasicObject> result = getSelectedFirstObject(basicObjects, x, y);
        isEmpty = !result.isPresent();

        if (!result.isPresent()) { //not match
            startX = x;
            startY = y;
        } else { // choose the first depth
            result.get().select();
        }
        this.draw();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        if (isEmpty) {
            double endX = e.getX();
            double endY = e.getY();
            double w = Math.abs(startX - endX);
            double h = Math.abs(startY - endY);
            Rectangle selectRect = new Rectangle(Math.min(startX, endX), Math.min(startY, endY), w, h);
            basicObjects.forEach(o -> {
                Rectangle rect = Rectangle.class.cast(o.getShape());
                if (selectRect.intersects(rect.getBoundsInLocal())) {
                    o.select();
                }
            });
        }
        this.draw();
    }

    @Override
    public void unGroup() {

        List<BasicObject> selectedComponents = getSelectedbasicObjects(basicObjects);
        componentList = EditorController.getInstance().getAllComponents();

        if (selectedComponents.size() == 1 && selectedComponents.get(0) instanceof Composite) {
            Composite composite = Composite.class.cast(selectedComponents.get(0));

            componentList.addAll(composite.getGroups());
            componentList.remove(composite);
            EditorController.getInstance().unSelectAll();
            factory.addDepth();
            this.draw();
        }
    }

    @Override
    public void group() {
        List<BasicObject> selectedObjects = getSelectedbasicObjects(basicObjects);
        if (selectedObjects.size() > 1) {
            componentList = EditorController.getInstance().getAllComponents();
            Rectangle rect = Rectangle.class.cast(selectedObjects.get(0).getShape());
            double minX = rect.getX();
            double minY = rect.getY();
            double maxX = rect.getX() + rect.getWidth();
            double maxY = rect.getY() + rect.getHeight();
            for (BasicObject object : selectedObjects) {
                rect = Rectangle.class.cast(object.getShape());
                minX = minX < rect.getX() ? minX : rect.getX();
                minY = minY < rect.getY() ? minY : rect.getY();
                maxX = maxX >= rect.getX() + rect.getWidth() ? maxX : rect.getX() + rect.getWidth();
                maxY = maxY >= rect.getY() + rect.getHeight() ? maxY : rect.getY() + rect.getHeight();
                componentList.remove(object);
            }
            Composite composite = factory.createComposite(minX, minY, maxX - minX, maxY - minY);

            selectedObjects.forEach(composite::addObject);
            EditorController.getInstance().unSelectAll();
            composite.select();
            componentList.add(composite);
            this.draw();
        }
    }

    @Override
    public void changeObjectName() {
        basicObjects = EditorController.getInstance().getAlBasicObjects();
        List<BasicObject> selectedObjects = getSelectedbasicObjects(basicObjects);
        if(!selectedObjects.isEmpty()){
            Optional<String> result = showNameDialog();
            result.ifPresent(name -> selectedObjects.get(0).setText(name));
        }
        this.draw();
    }

    private Optional<String> showNameDialog() {
        TextInputDialog dialog = new TextInputDialog("name1");
        dialog.setTitle("Change Object Name");
        dialog.setContentText("Please enter your name:");
        return dialog.showAndWait();
    }
}
