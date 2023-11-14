package shapes;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Shape{

    public static final Color DEFAULTCOLOR = Color.RED;
    protected Boolean fill;
    protected Color color; // the data that is common to ALL shapes
    protected int x;        // x,y is the top left corner of all the shapes (except triangle)
    protected int y;

    // default constructor
    public Shape() {
        this.color = DEFAULTCOLOR;
    }

    
    public Shape(int x, int y, String color, Boolean fill) {
        this.x = x;
        this.y = y;
        this.color = Color.decode(color);
        this.fill = fill;
    }

    abstract public void draw(Graphics g);

}
