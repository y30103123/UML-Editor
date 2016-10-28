package com.editor.component;

import com.editor.component.behavior.shape.IShapeBehavior;
import com.editor.services.Constants;
import com.editor.component.behavior.draw.IDrawBehavior;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;


/**
 * Created by y3010 on 2016/10/22.
 *
 * The BasicObject contain Class and Use Case type.
 */
public class BasicObject extends Component {

    private Point2D[] ports;

    public BasicObject(IDrawBehavior drawBehavior, IShapeBehavior shapeBehavior, Constants.TYPE type) {
        super(drawBehavior, shapeBehavior, type);
        this.ports = new Point2D[4];
        this.setText(type.equals(Constants.TYPE.CLASS) ? Constants.CLASS : Constants.CASE);
        IntStream.range(0, 4).forEach(i -> this.ports[i] = new Point2D.Double());
    }

    @Override
    public void setShape(Shape shape) {
        super.setShape(shape);
        this.updatePorts();
    }

    @Override
    public void moveTo(double x, double y) {
        super.moveTo(x, y);
        this.updatePorts();
    }

    public Point2D[] getPorts() {
        return this.ports;
    }

    /**
     * Find the suitable port .
     * Choose the minimum distance from point to these 4 ports
     * @param point target point
     * @return port of minimum distance port
     */
    public Point2D findSuitablePort(Point2D point) {

        // Calculate the distance and convert to array.
        double[] distances = Arrays.stream(ports).mapToDouble(point::distance).toArray();

        // Find the index of minimum distance .
        Optional<Integer> indexOptional = IntStream.range(0, distances.length).boxed().min(Comparator.comparingDouble(i -> distances[i]));
        int index = 0;
        if (indexOptional.isPresent()) {
            index = indexOptional.get();
        }
        return ports[index];
    }

    // Update location of 4 ports.
    private void updatePorts() {
        Rectangle rect = Rectangle.class.cast(this.getShape());
        double w = rect.getWidth();
        double h = rect.getHeight();
        double x = rect.getX();
        double y = rect.getY();
        double size = w / 10; // small Rectangle size
        ports[0].setLocation(x, y + h / 2 - size / 2);
        ports[1].setLocation(x + w / 2 - size / 2, y);
        ports[2].setLocation(x + w, y + h / 2 - size / 2);
        ports[3].setLocation(x + w / 2 - size / 2, y + h);
    }
}
