package com.editor.component.behavior.shape;

import com.editor.component.Component;
import javafx.scene.shape.Shape;

/**
 * Created by fish on 2016/10/24.
 */
public interface IShapeBehavior extends Cloneable {
    void setObject(Component component);
    void setShape(Shape shape);
    void moveTo(double x,double y);
    boolean isContain(double x,double y);
    Shape getShape();
    Object clone();
}
