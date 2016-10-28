package com.editor.component.behavior.shape;

import com.editor.component.Component;
import com.editor.component.ConnectionLine;
import com.sun.javafx.geom.Point2D;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;

/**
 * Created by y3010 on 2016/10/25.
 */
public class LineShape implements IShapeBehavior {

    private ConnectionLine object;
    private Polygon polygon;

    @Override
    public void moveTo(double x, double y) {
    }

    @Override
    public boolean isContain(double x, double y) {
        return false;
    }

    @Override
    public void setShape(Shape shape) {
        this.polygon = Polygon.class.cast(shape);
    }

    @Override
    public Shape getShape() {
        return this.polygon;
    }

    @Override
    public void setObject(Component component) {
        this.object = ConnectionLine.class.cast(component);
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
