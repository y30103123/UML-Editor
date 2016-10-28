package com.editor.component.behavior.shape;

import com.editor.component.BasicObject;
import com.editor.component.Component;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by fish on 2016/10/24.
 */
public class ObjectShape implements IShapeBehavior {

    private BasicObject object;
    private Rectangle rect;

    @Override
    public void moveTo(double x, double y) {
        updateShape(x, y);
    }

    @Override
    public boolean isContain(double x, double y) {
        return this.rect.contains(x, y);
    }

    @Override
    public void setShape(Shape shape) {
        this.rect = Rectangle.class.cast(shape);
        this.updateSize(rect.getWidth(), rect.getHeight());
    }

    @Override
    public Shape getShape() {
        return this.rect;
    }

    @Override
    public void setObject(Component component) {
        this.object = BasicObject.class.cast(component);
    }

    private void updateShape(double centerX, double centerY) {
        this.rect.setX(centerX - rect.getWidth() / 2);
        this.rect.setY(centerY - rect.getHeight() / 2);
    }

    private void updateSize(double w, double h) {
        this.rect.setWidth(w);
        this.rect.setHeight(h);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
