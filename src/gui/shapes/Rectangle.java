//ID: 316441534
package gui.shapes;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a rectangle.
 * Variables:
 * upperLeft - The upper left point of the rectangle
 * width - The rectangle's width
 * height - The rectangle's height
 * color - The rectangle's color
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    private Color stroke;
    private Image image;

    /**
     * Constructor with default size.
     *
     * @param upperLeft The upperLeft point to copy from
     * @param color     The rectangles color
     */
    public Rectangle(Point upperLeft, Color color) {
        this.upperLeft = new Point(upperLeft);
        this.width = 50;
        this.height = 20;
        this.color = color;
        this.stroke = Color.black;
    }

    /**
     * Constructor that copies data from other rectangle.
     *
     * @param rectangle The rectangle to copy from
     */
    public Rectangle(Rectangle rectangle) {
        this.upperLeft = new Point(rectangle.getUpperLeft());
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.color = rectangle.getColor();
        this.stroke = rectangle.getStroke();
        this.image = rectangle.getImage();
    }

    /**
     * gui.shapes.Rectangle's constructor with different parameters.
     *
     * @param upperLeft The upper left point of the rectangle
     * @param width     The rectangle's width
     * @param height    The rectangle's height
     * @param color     The rectangle's color
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.color = color;
        this.stroke = Color.black;
    }

    /**
     * @return The rectangle's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Create the rectangles 4 lines to check and returns them as a list.
     *
     * @return List of rectangle's 4 lines
     */
    private java.util.List<Line> createRectangleLines() {
        List<Line> lines = new ArrayList<>();
        //create the other 3 points of the rectangle
        Point upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        Point bottomRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        //create and add the 4 lines to a list and return it
        lines.add(new Line(this.upperLeft, upperRight));
        lines.add(new Line(bottomLeft, bottomRight));
        lines.add(new Line(this.upperLeft, bottomLeft));
        lines.add(new Line(upperRight, bottomRight));
        return lines;
    }

    /**
     * Finds all intersection points of the rectangle with a line. if there are none, return null
     *
     * @param line The line to check intersection with
     * @return List of points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<>();
        //create the 4 rectangle lines
        List<Line> lines = createRectangleLines();
        //for every line, find the intersection. if it exists, then add it to the list
        for (Line l : lines) {
            Point p = l.intersectionWith(line);
            if (p != null) {
                pointsList.add(p);
            }
        }
        //if there wasn't a point, return null
        if (pointsList.isEmpty()) {
            return null;
        }
        //return the list
        return pointsList;
    }

    /**
     * @return The upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set a new upperLeft point.
     *
     * @param x The new x
     * @param y The new y
     */
    public void setUpperLeft(double x, double y) {
        getUpperLeft().setX(x);
        getUpperLeft().setY(y);
    }

    /**
     * @return The rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Set a new newWidth for the background.
     *
     * @param newWidth The new newWidth to set
     */
    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    /**
     * @return The rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Set a new newHeight for the background.
     *
     * @param newHeight The new width to set
     */
    public void setHeight(double newHeight) {
        this.height = newHeight;
    }

    public Image getImage() {
        return this.image;
    }

    /**
     * The function sets an image for the rectangle.
     *
     * @param img
     */
    public void setImage(Image img) {
        this.image = img;
    }

    public void setStroke(Color newStroke) {
        this.stroke = newStroke;
    }

    public Color getStroke() {
        return this.stroke;
    }

    /**
     * Draws the rectangle on a drawSurface.
     *
     * @param d The drawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        if (this.image == null) {
            d.setColor(this.color);
            d.fillRectangle(this.upperLeft.getXInt(), this.upperLeft.getYInt(), (int) this.width, (int) this.height);
        } else {
            d.drawImage(this.upperLeft.getXInt(), this.upperLeft.getYInt(), this.image);
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle(this.upperLeft.getXInt(), this.upperLeft.getYInt(), (int) this.width, (int) this.height);
        }
    }
}
