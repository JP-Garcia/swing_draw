package shapes;

import java.awt.Graphics;

public class Line extends Shape{

    protected int x_f  = 0; // default width is 10
    protected int y_f = 0;  // default height is 5

    public Line() {
        // nothing to do, but required by java!
    }

    public Line(int x, int y, String color, Boolean fill) {
        super(x, y, color, fill);
    }
    
    public Line(int x, int y, int x_f, int y_f, String color, Boolean fill) {
        super(x, y, color, fill);
        this.x_f = x_f;
        this.y_f = y_f;
    }

    @Override
    public void draw(Graphics g) {
        if (g==null)
            System.out.println("drawing a " + color + " line");
        else {
            // int[] lisx = {x, x + width};
            // int[] lisy = {y, y + height};
            g.setColor(color);
            g.drawLine(x, y, x_f, y_f);
                // g.drawRect(x, y, width, height);
            }
        }
    }
