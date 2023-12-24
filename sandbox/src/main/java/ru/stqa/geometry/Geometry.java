package ru.stqa.geometry;

import ru.stqa.geometry.figures.Square;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100));
        var squares = Stream.generate(randomSquare).limit(5);

        squares.peek(Square::printSquareArea).forEach(Square::printPerimeter);


        //squares.forEach(Square::printSquareArea);

//        Consumer<Square> print = (square) -> {
//            Square.printSquareArea(square);
//        };
//        squares.forEach(print);


//        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));
//        Rectangle.printRectangleArea(new Rectangle(7.0, 9.0));
//
//        Triangle.printTrianglePerimeter(new Triangle(1.0, 2.0, 3.0));
//        Triangle.printTriangleArea(new Triangle(5., 6., 7.));
    }
}