package shapes;

public class Circle extends Oval {
    public Circle(int x, int y, int radius, String color) {
        super(x, y, radius *2, radius *2, color);
    }
}
