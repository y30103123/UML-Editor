package com.editor.component.behavior.draw;

import com.editor.services.Constants;
import com.editor.component.ConnectionLine;
import com.editor.component.Component;
import java.awt.geom.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

/**
 * Created by y3010 on 2016/10/23.
 */
public class DrawLine implements IDrawBehavior {

    private ConnectionLine object;

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.RED);
        Constants.TYPE type = this.object.getType();
        drawPolygon(g,type);
    }

    @Override
    public void setObject(Component component) {
        this.object = ConnectionLine.class.cast(component);
    }

    private void drawPolygon(GraphicsContext g, Constants.TYPE type) {

        Point2D begin = object.getBegin();
        Point2D end = object.getEnd();
        final int ARR_SIZE = 8;
        double dx = end.getX() - begin.getX(), dy = end.getY() - begin.getY();
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(begin.getX(), begin.getY());
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        g.setTransform(new Affine(transform));

        switch (type) {
            case ASSOCIATE:
                g.strokeLine(0, 0,len, 0);
                break;
            case GENERALIZATION:
                g.strokeLine(0, 0, len - ARR_SIZE, 0);
                g.strokePolygon(new double[]{len, len - ARR_SIZE, len - ARR_SIZE, len}, new double[]{0, -ARR_SIZE, ARR_SIZE, 0},
                        4);
                break;
            case COMPOSITION:
                g.strokeLine(0, 0, len - 2 * ARR_SIZE, 0);
                g.strokePolygon(new double[]{len - 2 * ARR_SIZE, len - ARR_SIZE, len, len - ARR_SIZE}, new double[]{0, -ARR_SIZE, 0, ARR_SIZE},
                        4);
                break;
            default:
        }
        // Clear Transform
        g.setTransform(new Affine());
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
