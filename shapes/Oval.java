package shapes;
import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Shape {

    protected int width = 10;
    protected int height = 5;

    public Oval(int x, int y, int width, int height, String color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }


    @Override
    public void draw(Graphics g) {
        if (g==null)
            System.out.println("drawing a " + width + "x" + height + " " + color + " oval");
        else {
            g.setColor(color);
            // g.drawOval(x, y, width, height);
            int x_top = x - abs(width);
            System.out.println(width + "-" + height);
            int y_top = y - abs(height);
            g.fillOval(x_top, y_top, abs(width*2), abs(height*2));

            g.setColor(Color.BLUE);
            // g.fillOval(x-1, y-1, 3, 3);

        }
    }
}
