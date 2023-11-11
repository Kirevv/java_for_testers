package ru.stqa.geometry.figures;

import java.util.Objects;

public record Rectangle (double side1, double side2) {

    public Rectangle {
        if (side1 < 0 || side2 < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative ");
        }
    }
    public static void printRectangleArea(Rectangle s) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", s.side1, s.side2, s.rectangleArea());
        System.out.println(text);
    }

    public double rectangleArea() {

        return this.side1 * this.side2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(this.side1, rectangle.side1) == 0 && Double.compare(this.side2, rectangle.side2) == 0)
                || (Double.compare(this.side1, rectangle.side2) == 0 && Double.compare(this.side2, rectangle.side1) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
