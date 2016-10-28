package com.editor.component;

import com.editor.component.behavior.draw.IDrawBehavior;
import com.editor.component.behavior.shape.IShapeBehavior;
import com.editor.services.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 * Created by y3010 on 2016/10/22.
 * <p>
 * Component is the basic component . The object and line , composite need to  extend this class , and behavior such as draw and shape through the  interface to execute
 * <p>
 * The Component. have functions , select , depth ,type ,text , the behavior of draw and shape.
 */
public abstract class Component {

    private IDrawBehavior drawBehavior;
    private IShapeBehavior shapeBehavior;

    private int depth;
    private boolean isSelected;
    private String text;
    protected Constants.TYPE type;

    public Component(IDrawBehavior drawBehavior, IShapeBehavior shapeBehavior, Constants.TYPE type) {
        this.shapeBehavior = shapeBehavior;
        this.drawBehavior = drawBehavior;
        this.drawBehavior.setObject(this);
        this.shapeBehavior.setObject(this);
        this.setType(type);
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void select() {
        this.isSelected = true;
    }

    public void unSelect() {
        this.isSelected = false;
    }

    public void draw(GraphicsContext g) {
        this.drawBehavior.draw(g);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Constants.TYPE getType() {
        return this.type;
    }

    public void setType(Constants.TYPE type) {
        this.type = type;
    }

    public void setDepth(int depth) {
        this.depth = depth;
        this.setText(text + String.valueOf(depth));
    }

    public int getDepth() {
        return this.depth;
    }

    public void moveTo(double x, double y) {
        this.shapeBehavior.moveTo(x, y);
    }

    public boolean isContain(double x, double y) {
        return this.shapeBehavior.isContain(x, y);
    }

    public void setShape(Shape shape) {
        this.shapeBehavior.setShape(shape);
    }

    public Shape getShape() {
        return this.shapeBehavior.getShape();
    }

}
