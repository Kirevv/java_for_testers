package ru.stqa.geometry.figures;

public record Square(double side) {

    public Square {
        if (side <0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        var text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.squareArea());
        System.out.println(text);
    }

    public static void printPerimeter(Square s) {
        var text = String.format("Периметр квадрата со стороной %f = %f", s.side, s.squarePerimeter());
        System.out.println(text);
    }


    public double squareArea() {
        return this.side * this.side;
    }

    public double squarePerimeter() {
        return this.side * 4;
    }
}