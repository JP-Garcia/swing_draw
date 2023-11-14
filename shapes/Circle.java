package shapes;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;


public class Circle extends Oval {
    public Circle(int x, int y, int width, int height, String color, Boolean fill) {
        super(x, y, hyp(width, height), hyp(width, height), color, fill);
    }
        public Circle(int x, int y, int radius, String color, Boolean fill) {
        super(x+radius, y+radius, radius/2, radius/2, color, fill);
    }
    public static int hyp(int a, int b){
        double hyp = sqrt(pow(a, 2) + pow(b, 2));
        return (int)hyp;
    }
}
