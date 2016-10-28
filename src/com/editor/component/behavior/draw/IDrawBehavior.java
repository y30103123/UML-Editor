package com.editor.component.behavior.draw;

import com.editor.component.Component;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by y3010 on 2016/10/23.
 */
public interface IDrawBehavior extends Cloneable{
    void draw(GraphicsContext g);
    void setObject(Component component);
    Object clone();
}
