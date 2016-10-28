package com.editor.mode;

import com.editor.EditorController;
import com.editor.component.*;
import com.editor.services.Constants;
import javafx.scene.input.MouseEvent;

import java.awt.geom.Point2D;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by fish on 2016/10/25.
 */
public class LineMode extends Mode {

    private double startX;
    private double startY;
    private boolean hasStartPoint;
    private BasicObject startObj;
    private static Mode instance;

    public static Mode getInstance() {
        if (instance == null) {
            instance = new LineMode();
        }
        return instance;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        basicObjects = EditorController.getInstance().getAlBasicObjects();
        EditorController.getInstance().unSelectAll();
        double x = e.getX();
        double y = e.getY();

        Optional<BasicObject> result = getSelectedFirstObject(basicObjects, x, y);

        hasStartPoint = result.isPresent();

        if (result.isPresent()) { // selected
            startX = x;
            startY = y;
            startObj = findStartObject(result.get(), x, y);
        }
        hasStartPoint = startObj != null;
        this.draw();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        if (hasStartPoint) {
            double endX = e.getX();
            double endY = e.getY();
            Optional<BasicObject> result = getSelectedFirstObject(basicObjects, endX, endY);
            if (result.isPresent()) {
                Constants.TYPE type = EditorController.getInstance().getCurrentType();
                BasicObject endObject = result.get();
                if (!endObject.equals(startObj)) {

                    Point2D startPort = startObj.findSuitablePort(new Point2D.Double(endX, endY));
                    Point2D endPort = findEndPort(endObject, new Point2D.Double(endX, endY));

                    Component component = factory.createLine(type, startPort, endPort);
                    EditorController.getInstance().getAllComponents().add(component);
                    this.draw();
                }
            }
        }
    }

    private BasicObject findStartObject(BasicObject obj, double x, double y) {
        BasicObject startObj = null;
        if (obj instanceof Composite) {
            Composite composite = Composite.class.cast(obj);
            Optional<BasicObject> realResult = getSelectedFirstObject(composite.getGroups()
                            .stream()
                            .collect(Collectors.toList()), x, y);
            if (realResult.isPresent()) {
                startObj = findStartObject(realResult.get(),x,y);
            }
        } else {
            startObj = obj;
        }
        return startObj;
    }

    private Point2D findEndPort(BasicObject obj, Point2D endPoint) {
        Point2D endPort = null;
        if (obj instanceof Composite) {
            Composite composite = Composite.class.cast(obj);
            Optional<BasicObject> result = composite.getGroups().stream()
                    .filter(c -> c.isContain(endPoint.getX(), endPoint.getY()))
                    .findFirst();
            if (result.isPresent()) {
                endPort = findEndPort(result.get(), endPoint);
            }
        } else {
            endPort = obj.findSuitablePort(new Point2D.Double(startX, startY));
        }

        return endPort;
    }
}
