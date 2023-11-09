package shapes;

public class Square extends Rectangle {

    public static int calculateSideLenght(int width, int height) {
        int aw = Math.abs(width);
        int ah = Math.abs(height);
        if ((aw) > (ah)) {
            return aw;
        }
        return ah;
    }
    
    public static int isPositive(int amogus) {
        if (amogus >= 0) {
            return 1;
        }
        else return -1;
    }

    public Square(int x, int y, int width, int height, String color, Boolean fill) {
        super(x, y, isPositive(width) * calculateSideLenght(width, height), isPositive(height) * calculateSideLenght(width, height), color, fill);
    }

    
}
