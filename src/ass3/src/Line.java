//ID: 316441534

/**
 * The class represents a line as in a cartesian coordinate system.
 * Parameters:
 * start - The line's starting point
 * end - The line's ending point
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor with 2 points.
     *
     * @param start The starting point of the lie
     * @param end   The ending point of the lie
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }

    /**
     * Constructor with 4 decimal numbers for starting and ending points.
     *
     * @param x1 X value of the starting point
     * @param y1 Y value of the starting point
     * @param x2 X value of the ending point
     * @param y2 Y value of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return The length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The function finds the x, and y values of the middle of the line, and return the point.
     *
     * @return The middle point on the line
     */
    public Point middle() {
        double pointX = (this.start.getX() + this.end.getX()) / 2;
        double pointY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(pointX, pointY);
    }

    /**
     * @return Returns the starting point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return Returns the ending point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function calculates the slope of the line.
     *
     * @return The slope of the line
     */
    public double findSlope() {
        return ((this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX()));
    }

    /**
     * The function checks if two lines are sharing only one point.
     *
     * @param other The other line to check with
     * @return The point if there is and null otherwise
     */
    private Point isSharingOnePoint(Line other) {
        //Check if they share only one point
        if ((this.start.equals(other.start) || this.start.equals(other.end))
                ^ (this.end.equals(other.start) || this.end.equals(other.end))) {
            //If they are, return the point
            if ((this.start.equals(other.start) || this.start.equals(other.end))) {
                return this.start;
            } else {
                return this.end;
            }
        }
        return null;
    }

    /**
     * The function checks if a point is on a line.
     * <p>
     * The function gets checks if the intersection point is between the ending and starting point
     * of the line.
     * </p>
     *
     * @param pointToCheck intersection point of 2 lines
     * @return True if the point is one the line, and false otherwise
     */
    private boolean isPointOnLine(Point pointToCheck) {
        if (pointToCheck == null) {
            return false;
        }
        double minX, maxX, minY, maxY;
        // Finds the minimum and maximum x and y values for the line
        minX = Math.min(this.start().getX(), this.end().getX());
        maxX = Math.max(this.start().getX(), this.end().getX());
        minY = Math.min(this.start().getY(), this.end().getY());
        maxY = Math.max(this.start().getY(), this.end().getY());
        // If the pointToCheck X and Y are not between the line's, return false.
        // Else, return true.
        return (!(pointToCheck.getX() > maxX) && !(pointToCheck.getX() < minX))
                && ((!(pointToCheck.getY() > maxY) && !(pointToCheck.getY() < minY)));
    }

    /**
     * function that helps to check if one line is overlapping another.
     * <p>
     * the function gets 4 X or 4 Y and checks if max or min of one line is between the numbers of another line
     * </p>
     *
     * @param num1 X/Y of line1 end/start
     * @param num2 X/Y of line1 end/start
     * @param num3 X/Y of line2 end/start
     * @param num4 X/Y of line2 end/start
     * @return true if there is an overlap and false otherwise
     */
    private boolean isOverlapping(double num1, double num2, double num3, double num4) {
        double maxLine1, minLine1, maxLine2, minLine2;
        maxLine1 = Math.max(num1, num2);
        minLine1 = Math.min(num1, num2);
        maxLine2 = Math.max(num3, num4);
        minLine2 = Math.min(num3, num4);
        //checks the maximum for both lines
        if (maxLine1 > minLine2 && maxLine1 < maxLine2 || maxLine2 > minLine1 && maxLine2 < maxLine1) {
            return true;
        }
        //checks the minimum for both lines
        if (minLine1 > minLine2 && minLine1 < maxLine2 || minLine2 > minLine1 && minLine2 < maxLine1) {
            return true;
        }
        return false;
    }

    /**
     * The function handles and finds the intersection in case at least one of the function is x = a.
     *
     * @param other      The other line to check with
     * @param mySlope    My slope that was pre calculated
     * @param otherSlope other slope that was pre calculated
     * @return The intersection point if exist and false otherwise
     */
    private Point infiniteCase(Line other, double mySlope, double otherSlope) {
        boolean myInfinite = Double.isInfinite(mySlope);
        boolean otherInfinite = Double.isInfinite(otherSlope);
        double x, y;
        //if both lines are x = a
        if (myInfinite && otherInfinite) {
            //if both lines have the same x value
            if (this.start.getX() == other.start.getX()) {
                //check if there are overlapping
                boolean check = isOverlapping(this.start.getY(), this.end.getY(), other.start.getY(), other.end.getY());
                //if they are not, check if they share the same point
                if (!check) {
                    return isSharingOnePoint(other);
                }
            }
            return null;
        }
        if (myInfinite) { //find the y if only one of the lines is x = a
            x = this.start.getX();
            y = otherSlope * x - otherSlope * other.start.getX() + other.start.getY();
        } else {
            x = other.start.getX();
            y = mySlope * x - mySlope * this.start.getX() + this.start.getY();
        }
        Point intersection = new Point(x, y);
        //if the intersection is between both lines
        if (other.isPointOnLine(intersection) && this.isPointOnLine(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * The function calculates the intersection point between two lines as if they were regular linear functions.
     *
     * @param other      other line to check with
     * @param mySlope    this line's slope
     * @param otherSlope other line's slope
     * @return The intersection point as if both lines are linear functions
     */
    public Point getIntersectionByFunctions(Line other, double mySlope, double otherSlope) {
        double intersectionX, intersectionY;
        //Find intersection X by equation: X = (m1X1-m2X2+Y2-Y1)/(m1-m2)
        intersectionX = (mySlope * this.start.getX() - otherSlope * other.start.getX());
        intersectionX = intersectionX + other.start.getY() - this.start.getY();
        intersectionX = intersectionX / (mySlope - otherSlope);
        //If one of the lines is y=a, then get they a
        if (mySlope == 0) {
            intersectionY = this.start.getY();
        } else if (otherSlope == 0) {
            intersectionY = other.start.getY();
        } else {
            //Find intersection Y by equation: Y = m(intersectionX - X1) + Y1
            intersectionY = mySlope * (intersectionX - this.start.getX()) + this.start.getY();
        }
        return new Point(intersectionX, intersectionY);
    }

    /**
     * The function finds the intersection point between any two lines.
     *
     * @param other The other line to intersect with
     * @return The intersection point if exists and false otherwise
     */
    public Point intersectionWith(Line other) {
        double mySlope = this.findSlope();
        double otherSlope = other.findSlope();
        //special case if one of the lines is x = a
        if (Double.isInfinite(mySlope) || Double.isInfinite(otherSlope)) {
            return infiniteCase(other, mySlope, otherSlope);
        } else if (mySlope == otherSlope) { //if both lines are parallel, check if they are a continuing of another.
            //check if there is an overlap
            boolean checkYOverlap = isOverlapping(this.start.getY(),
                    this.end.getY(), other.start.getY(), other.end.getY());
            boolean checkXOverlap = isOverlapping(this.start.getX(),
                    this.end.getX(), other.start.getX(), other.end.getX());
            if (!(checkXOverlap || checkYOverlap)) {
                return isSharingOnePoint(other);
            }
            return null;
        }
        //If non of the above happened, then find the the intersection point by treating them as linear functions
        Point point = getIntersectionByFunctions(other, mySlope, otherSlope);
        //If the intersection is on both lines, that they are intersect
        if (this.isPointOnLine(point) && other.isPointOnLine(point)) {
            return point;
        }
        return null;
    }

    /**
     * The function gets a rectangle, and finds the closest intersection point to Line's starting point.
     *
     * @param rect Rectangle to check intersection with
     * @return The closest intersection point. if there isn't one, return null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //gets a list of intersection points with line, and find the closest point to start.
        return start.findClosestPoint(rect.intersectionPoints(this));
    }

    /**
     * The function checks if two lines are intersecting.
     *
     * @param other The other lines to intersect with
     * @return true if they intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * @param other Other line to compare with
     * @return Return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start))
                && (this.end.equals(other.end)));
    }

}
