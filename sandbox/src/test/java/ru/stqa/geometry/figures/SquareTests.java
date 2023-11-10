package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {

        var s = new Square(5.0);
        double result = s.squareArea();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {

        var p = new Square(5.0);
        double result = p.squarePerimeter();
        Assertions.assertEquals(20., result);
    }
}