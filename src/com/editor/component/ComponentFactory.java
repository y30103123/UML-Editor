package com.editor.component;

import com.editor.component.behavior.draw.*;
import com.editor.component.behavior.shape.CompositeShape;
import com.editor.component.behavior.shape.IShapeBehavior;
import com.editor.component.behavior.shape.LineShape;
import com.editor.component.behavior.shape.ObjectShape;
import com.editor.services.Constants;

import java.awt.geom.Point2D;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * Created by fish on 2016/10/24.
 */
public class ComponentFactory {

    private int depth;
    private IDrawBehavior drawLine;
    private IDrawBehavior drawObject;
    private IDrawBehavior drawComposite;

    private IShapeBehavior objectShape;
    private IShapeBehavior lineShape;
    private IShapeBehavior compositeShape;

    private static ComponentFactory factory;

    public ComponentFactory() {
        this.drawLine = new DrawLine();
        this.drawObject = new DrawObject();
        this.drawComposite = new DrawComposite();
        this.objectShape = new ObjectShape();
        this.lineShape = new LineShape();
        this.compositeShape = new CompositeShape();
        this.depth = 99;
    }

    public static ComponentFactory getInstance() {
        if (factory == null) {
            factory = new ComponentFactory();
        }
        return factory;
    }

    public void addDepth(){
        this.depth++;
    }

    public Composite createComposite(double x, double y, double w, double h) {
        Composite component;
        component = new Composite(cloneDrawBehavior(drawComposite), cloneShapeBehavior(compositeShape), Constants.TYPE.COMPOSITE);
        component.setShape(new Rectangle(x, y, w, h));
        component.setDepth(depth--);
        return component;
    }

    public Component createObject(Constants.TYPE type, double x, double y) {
        Component component = null;
        double size = Constants.DEFAULT_SIZE;
        switch (type) {
            case CASE:
                component = new BasicObject(cloneDrawBehavior(drawObject), cloneShapeBehavior(objectShape), type);
                double ovalSize = size + size / 2;
                component.setShape(new Rectangle(x, y, ovalSize, size));
                component.setDepth(depth--);
                break;
            case CLASS:
                component = new BasicObject(cloneDrawBehavior(drawObject), cloneShapeBehavior(objectShape), type);
                component.setShape(new Rectangle(x, y , size, size));
                component.setDepth(depth--);
                break;
            default:
        }
        return component;
    }

    public Component createLine(Constants.TYPE type, Point2D startPort, Point2D endPort) {
        Component component = null;
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(startPort.getX(), startPort.getY(), endPort.getX(), endPort.getY());

        switch (type) {
            case ASSOCIATE:
            case GENERALIZATION:
            case COMPOSITION:
                component = new ConnectionLine(cloneDrawBehavior(drawLine), cloneShapeBehavior(lineShape), type);
                component.setShape(polygon);

                ConnectionLine line = ConnectionLine.class.cast(component);
                line.setBegin(startPort);
                line.setEnd(endPort);

                break;
            default:
        }
        return component;
    }

    private IDrawBehavior cloneDrawBehavior(IDrawBehavior behavior) {
        return (IDrawBehavior) behavior.clone();
    }

    private IShapeBehavior cloneShapeBehavior(IShapeBehavior behavior) {
        return (IShapeBehavior) behavior.clone();
    }
}
