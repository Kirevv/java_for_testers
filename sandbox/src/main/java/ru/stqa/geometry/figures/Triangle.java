package ru.stqa.geometry.figures;

import java.util.Objects;

import static java.lang.Math.sqrt;

public record Triangle (double side1, double side2, double side3) {

    public Triangle {
        if (side1 <0 || side2 <0 || side3 < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new IllegalArgumentException("Two sides sum should not be less than the other third side");
        }
    }

    public static void printTrianglePerimeter(Triangle p) {
        var text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f = %.2f", p.side1, p.side2, p.side3, p.perimeter());
        System.out.println(text);
    }

    public double perimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public static void printTriangleArea(Triangle s) {
        var text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f = %.2f", s.side1, s.side2, s.side3, s.area());
        System.out.println(text);
    }

    public double area() {
        var p = (this.side1 + this.side2 + this.side3) / 2;
        return sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0)
                || (Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side3, triangle.side3) == 0)
                || (Double.compare(this.side1, triangle.side3) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side3, triangle.side1) == 0)
                || (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side2, triangle.side3) == 0 && Double.compare(this.side3, triangle.side2) == 0)
                || (Double.compare(this.side1, triangle.side3) == 0 && Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side3, triangle.side2) == 0)
                || (Double.compare(this.side3, triangle.side1) == 0 && Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side2, triangle.side3) == 0)
                || (Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
