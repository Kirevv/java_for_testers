public class Geometry {
    public static void main(String[] args) {
        printSquareArea(7.);
        printSquareArea(8.);
        printSquareArea(10.);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(7.0, 9.0);
    }

    private static void printRectangleArea(double side1, double side2) {
        System.out.println("Площадь прямоугольника со сторонами " + side1 + " и " + side2 + " = " + rectangleArea(side1, side2));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
