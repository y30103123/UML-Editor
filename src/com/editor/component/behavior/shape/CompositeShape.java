package com.editor.component.behavior.shape;

import com.editor.component.Component;
import com.editor.component.Composite;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by y3010 on 2016/10/26.
 */
public class CompositeShape implements IShapeBehavior {

    private Composite composite;
    private Rectangle rect;
    private double lastX;
    private double lastY;

    @Override
    public void setObject(Component component) {
        composite = Composite.class.cast(component);
    }

    @Override
    public void setShape(Shape shape) {
        rect = Rectangle.class.cast(shape);
        updateLast(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
    }

    @Override
    public Shape getShape() {
        return rect;
    }

    @Override
    public void moveTo(double x, double y) {
        updateShape(x, y);
        double offsetX = x - lastX;
        double offsetY = y - lastY;
        composite.getGroups().forEach(c -> {
            Rectangle r = Rectangle.class.cast(c.getShape());
            double realX = r.getX() + r.getWidth() / 2 + offsetX;
            double realY = r.getY() + r.getHeight() / 2 + offsetY;
            c.moveTo(realX, realY);
        });
        updateLast(x, y);
    }

    @Override
    public boolean isContain(double x, double y) {
        return rect.contains(x, y);
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

    private void updateShape(double centerX, double centerY) {
        this.rect.setX(centerX - rect.getWidth() / 2);
        this.rect.setY(centerY - rect.getHeight() / 2);
    }

    private void updateLast(double x, double y) {
        this.lastX = x;
        this.lastY = y;
    }
}
