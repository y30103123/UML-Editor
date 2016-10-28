package com.editor.component.behavior.draw;

import com.editor.component.Component;
import com.editor.component.Composite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Created by y3010 on 2016/10/26.
 */
public class DrawComposite implements IDrawBehavior {

    private Composite composite;

    @Override
    public void draw(GraphicsContext g) {
        composite.getGroups().forEach(c -> c.draw(g));
        drawText(g);
        drawRect(g);
    }

    @Override
    public void setObject(Component component) {
        composite = Composite.class.cast(component);
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

    private void drawText(GraphicsContext g) {
        String text = composite.getText();
        Rectangle rect = Rectangle.class.cast(composite.getShape());

        double x = rect.getX();
        double y = rect.getY() - rect.getHeight() / 30;

        g.setFill(Color.BLACK);
        g.setFont(new Font("Arial", 20));
        g.fillText(text, x, y);
    }

    private void drawRect(GraphicsContext g) {
        g.setFill(Color.BLACK);

        g.setLineDashes(2d);
        Rectangle rect = Rectangle.class.cast(composite.getShape());

        double x = rect.getX();
        double y = rect.getY();
        double w = rect.getWidth();
        double h = rect.getHeight();

        g.strokeRoundRect(x, y, w, h, 10, 10);
        g.setLineDashes(0);
    }
}
