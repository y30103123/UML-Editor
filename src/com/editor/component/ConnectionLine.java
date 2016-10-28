package com.editor.component;

import com.editor.component.behavior.shape.IShapeBehavior;
import com.editor.services.Constants;
import com.editor.component.behavior.draw.IDrawBehavior;

import java.awt.geom.Point2D;


/**
 * Created by y3010 on 2016/10/22.
 */
public class ConnectionLine extends Component {

    private Point2D begin;
    private Point2D end;

    public ConnectionLine(IDrawBehavior drawBehavior, IShapeBehavior shapeBehavior, Constants.TYPE type) {
        super(drawBehavior, shapeBehavior, type);
        this.setType(type);
    }

    public Point2D getBegin() {
        return this.begin;
    }

    public Point2D getEnd() {
        return this.end;
    }

    public void setBegin(Point2D port) {
        this.begin = port;
    }

    public void setEnd(Point2D port) {
        this.end = port;
    }

}
