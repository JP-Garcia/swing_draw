package shapes;

import java.awt.Graphics;
// import java.awt.event.MouseEvent;

public class Rectangle extends Shape {

    protected int width  = 10; // default width is 10
    protected int height = 5;  // default height is 5
    private Boolean fill = false;

    public Rectangle() {
        // nothing to do, but required by java!
    }

    /*
    public Rectangle(int x, int y, String color) {
        super(x, y, color);

    }
    */
    
    public Rectangle(int x, int y, int width, int height, String color, Boolean fill) {
        super(x, y, color);
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    @Override
    public void draw(Graphics g) {
        if (g==null)
            System.out.println("drawing a " + width + "x" + height + " " + color + " rectangle");
        else {
            // int[] lisx = {x, x + width};
            // int[] lisy = {y, y + height};
            g.setColor(color);
            if (fill) {g.fillRect(x, y, width, height);}
            else {
                g.drawLine(x, y, x + width, y);
                g.drawLine(x + width, y, x + width, y + height);
                g.drawLine(x + width, y + height, x, y + height);
                g.drawLine(x, y + height, x, y);
                // g.drawRect(x, y, width, height);
            }
        }
    }
}
