import java.io.File;

public class hello {
    public static void main(String[] args) {
        int z = calculate();
        System.out.println(z);
        System.out.println("Hello, world!");

    }

    private static int calculate() {
        var x = 1;
        var y = 0;
        int z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}