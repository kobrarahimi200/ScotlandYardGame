/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import logic.Position;

/**
 *
 * @author Reyhan
 */
public class DrawCircle {

    //DrawCircle circle = new DrawCircle();
    private Color color;
    private int radius;
    private Position pos;
    private Circle circle;
    private Polygon polygon;

    DrawCircle(Position pos, int radius, Color color) {
        this.pos = pos;
        this.color = color;
        this.radius = radius;
        circle = new Circle(pos.x(), pos.y(), 10);
        polygon = new Polygon();
        double xNorm = pos.x();
        double yNorm = pos.y();
        polygon.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -15.0, 15.0,
            15.0, 15.0});
    }
}
