package com.epam.rd.autotasks.triangle;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;

class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
        if ((start.getX() == end.getX()) &&
                (start.getY() == end.getY())) {
            throw new IllegalArgumentException("You can't make side with two equal dots");
        }
    }
    double length() {
        return sqrt(
                pow(end.getX() - start.getX(), 2) +
                pow(end.getY() - start.getY(), 2));
    }
    boolean inRange(Segment segment, Point intersection){
        double minX_First = min(start.getX(), end.getX());
        double maxX_First = max(start.getX(), end.getX());
        double minX_Second = min(segment.start.getX(), segment.end.getX());
        double maxX_Second = max(segment.start.getX(), segment.end.getX());

        return minX_First <= intersection.getX() &&
                intersection.getX() <= maxX_First &&
                minX_Second <= intersection.getX() &&
                intersection.getX() <= maxX_Second;
    }
    Point middle() {
        return new Point((start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2 );
    }
    Point intersection(Segment another) {
        final double a;
        final double b;
        final double c;
        a = (start.getX() * end.getY()) - (start.getY() * end.getX());
        b = (another.start.getX() * another.end.getY()) - (another.start.getY() * another.end.getX());
        c = (start.getX() - end.getX()) * (another.start.getY() - another.end.getY()) -
                (start.getY() - end.getY()) * (another.start.getX() - another.end.getX());
        Point result = new Point((a * (another.start.getX() - another.end.getX()) - b * (start.getX() - end.getX())) / c,
                (a * (another.start.getY() - another.end.getY()) - b * (start.getY() - end.getY())) / c);

        if ((c != 0) && inRange(another, result)){
            return result;
        }else {return null;}
    }
}
