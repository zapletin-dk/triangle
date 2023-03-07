package com.epam.rd.autotasks.triangle;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;

class Segment {
    private final Point startPointOfSegment;
    private final Point endPointOfSegment;
    public Segment(Point startPointOfSegment, Point endPointOfSegment) {
        this.startPointOfSegment = startPointOfSegment;
        this.endPointOfSegment = endPointOfSegment;
        if ((startPointOfSegment.getX() == endPointOfSegment.getX()) &&
                (startPointOfSegment.getY() == endPointOfSegment.getY())){
            throw new IllegalArgumentException("You can't make side with two equal dots");
        }
    }
    double length() {
        return sqrt(
                pow(endPointOfSegment.getX() - startPointOfSegment.getX(), 2) +
                        pow(endPointOfSegment.getY() - startPointOfSegment.getY(), 2) );
    }

    boolean intersectionInRangeOfSegments(Segment anotherSegment, Point intersection){
        double minX_First = min(startPointOfSegment.getX(), endPointOfSegment.getX());
        double maxX_First = max(startPointOfSegment.getX(), endPointOfSegment.getX());
        double minX_Second = min(anotherSegment.startPointOfSegment.getX(), anotherSegment.endPointOfSegment.getX());
        double maxX_Second = max(anotherSegment.startPointOfSegment.getX(), anotherSegment.endPointOfSegment.getX());

        return minX_First <= intersection.getX() &&
                intersection.getX() <= maxX_First &&
                minX_Second <= intersection.getX() &&
                intersection.getX() <= maxX_Second;
    }
    Point middle() {
        return new Point((startPointOfSegment.getX() + endPointOfSegment.getX()) / 2,
                (startPointOfSegment.getY() + endPointOfSegment.getY()) / 2 );
    }

    /**
     * This method finds the intersection of two segments via formula using their coordinates
     * for more information look here <a href="https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection">wiki</a>
     */
    Point intersection(Segment another) {
        final double a;
        final double b;
        final double c;
        a = (startPointOfSegment.getX() * endPointOfSegment.getY()) - (startPointOfSegment.getY() * endPointOfSegment.getX());
        b = (another.startPointOfSegment.getX() * another.endPointOfSegment.getY()) - (another.startPointOfSegment.getY() * another.endPointOfSegment.getX());
        c = (startPointOfSegment.getX() - endPointOfSegment.getX()) * (another.startPointOfSegment.getY() - another.endPointOfSegment.getY()) -
            (startPointOfSegment.getY() - endPointOfSegment.getY()) * (another.startPointOfSegment.getX() - another.endPointOfSegment.getX());
        Point result = new Point((a * (another.startPointOfSegment.getX() - another.endPointOfSegment.getX()) - b * (startPointOfSegment.getX() - endPointOfSegment.getX())) / c,
                (a * (another.startPointOfSegment.getY() - another.endPointOfSegment.getY()) - b * (startPointOfSegment.getY() - endPointOfSegment.getY())) / c);

        if ((c != 0) && intersectionInRangeOfSegments(another, result)){
            return result;
        } else {return null;}
    }
}
