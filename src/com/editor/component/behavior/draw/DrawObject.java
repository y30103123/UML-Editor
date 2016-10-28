package com.editor.component.behavior.draw;

import com.editor.services.Constants;
import com.editor.component.BasicObject;
import com.editor.component.Component;

import java.awt.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


/**
 * Created by y3010 on 2016/10/23.
 */
public class DrawObject implements IDrawBehavior {

    private BasicObject object;
    private Rectangle rect;

    @Override
    public void draw(GraphicsContext g) {
        Constants.TYPE type = this.object.getType();
        rect = Rectangle.class.cast(object.getShape());
        drawObject(g, type);
        drawFocus(g);
        drawText(g);
    }

    @Override
    public void setObject(Component component) {
        this.object = BasicObject.class.cast(component);
    }

    private void drawText(GraphicsContext g) {
        String text = object.getText();

        double x = rect.getX() + rect.getWidth() / 5;
        double y = rect.getY() + rect.getHeight() / 3;

        g.setFill(Color.BLACK);
        g.setFont(new Font("Arial", 20));
        g.fillText(text, x, y);

    }

    private void drawFocus(GraphicsContext g) {
        if (object.isSelected()) {
            Point2D[] ports = object.getPorts();
            double size = rect.getHeight() / 10;

            g.setFill(Color.BLACK);
            g.fillRect(ports[0].getX() - size, ports[0].getY(), size, size);
            g.fillRect(ports[1].getX(), ports[1].getY() - size, size, size);
            g.fillRect(ports[2].getX(), ports[2].getY(), size, size);
            g.fillRect(ports[3].getX(), ports[3].getY(), size, size);
        }
    }

    private void drawObject(GraphicsContext g, Constants.TYPE type) {
        switch (type) {
            case CASE:
                drawCase(g);
                break;
            case CLASS:
                drawClass(g);
                break;
            default:
        }
    }

    private void drawClass(GraphicsContext g) {
        double w = rect.getWidth();
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();
        double radius = 10;
        double interval = h / 3;

        g.setFill(Color.web(Constants.CLASS_COLOR));
        g.fillRoundRect(x, y, w, h, radius, radius);
        g.setFill(Color.BLACK);
        g.strokeLine(x, y + interval, x + w, y + interval);
        g.strokeLine(x, y + interval * 2, x + w, y + interval * 2);
    }

    private void drawCase(GraphicsContext g) {
        double w = rect.getWidth();
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();
        g.setFill(Color.web(Constants.CASE_COLOR));
        g.fillOval(x, y, w, h);
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
