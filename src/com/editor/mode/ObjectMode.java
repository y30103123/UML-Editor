package com.editor.mode;

import com.editor.EditorController;
import com.editor.component.Component;
import com.editor.services.Constants;
import javafx.scene.input.MouseEvent;

/**
 * Created by fish on 2016/10/25.
 */
public class ObjectMode extends Mode {

    private static Mode instance;

    public static Mode getInstance(){
        if(instance==null){
            instance = new ObjectMode();
        }
        return instance;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        Constants.TYPE type = EditorController.getInstance().getCurrentType();
        double x = e.getX();
        double y = e.getY();

        this.createObject(type, x, y);
        this.draw();
    }

    private void createObject(Constants.TYPE type, double x, double y) {
        Component component = factory.createObject(type, x, y);
        EditorController.getInstance().getAllComponents().add(component);
    }
}
