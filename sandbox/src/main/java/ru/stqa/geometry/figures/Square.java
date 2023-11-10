package ru.stqa.geometry.figures;

public record Square(double side) {

    public static void printSquareArea(Square s) {
        var text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.squareArea());
        System.out.println(text);
    }


    public double squareArea() {
        return this.side * this.side;
    }

    public double squarePerimeter() {
        return this.side * 4;
    }
}