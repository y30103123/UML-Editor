package com.editor.component;

import com.editor.component.behavior.draw.IDrawBehavior;
import com.editor.component.behavior.shape.IShapeBehavior;
import com.editor.services.Constants;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y3010 on 2016/10/22.
 *
 * Composite is the container of Basic Object.
 */
public class Composite extends BasicObject {

    private List<BasicObject> groups;

    public Composite(IDrawBehavior drawBehavior, IShapeBehavior shapeBehavior, Constants.TYPE type) {
        super(drawBehavior,shapeBehavior,type);
        this.groups = new ArrayList<>();
        this.setText("Group");
    }

    public void addObject(BasicObject component) {
        this.groups.add(component);
    }

    public List<BasicObject> getGroups(){
        return groups;
    }

    @Override
    public void select() {
        super.select();
        this.groups.forEach(Component::select);
    }

    @Override
    public void unSelect() {
        super.unSelect();
        this.groups.forEach(Component::unSelect);
    }
}
