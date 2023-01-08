package com.epam.rd.autotasks.triangle;
import java.util.Arrays;

import static java.lang.Math.sqrt;

class Triangle {
    private final Point a;
    private final Point b;
    private final Point c;
    private final Segment side_AB;
    private final Segment side_BC;
    private final Segment side_AC;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        side_AB = new Segment(a, b);
        side_BC = new Segment(b, c);
        side_AC = new Segment(a, c);
        if(!sideCondition()){
            throw new IllegalArgumentException("Not a triangle at all");
        }
    }
    public boolean sideCondition(){
        double []sides = {side_AC.length(), side_BC.length(), side_AB.length()};
        Arrays.sort(sides);
        System.out.println(Arrays.toString(sides));
        return (sides[0] + sides[1] > sides [2]);
    }
    public double halfPerimeter(){
        return (side_AB.length() + side_AC.length() + side_BC.length()) / 2 ;
    }
    public double area() {
        return sqrt(halfPerimeter() * (halfPerimeter() - side_BC.length()) * (halfPerimeter() - side_AB.length()) * (halfPerimeter() - side_AC.length()));
    }

    public Point centroid(){
        return new Segment(c, side_AB.middle()).intersection(new Segment(b, side_AC.middle()));
    }

}
