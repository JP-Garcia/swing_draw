package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Poly extends Shape {

    // protected ArrayList<Integer> x;
    // protected ArrayList<Integer> y;
    int x_prim[];
    int y_prim[];
    int len;

    public Poly(ArrayList<Integer> x, ArrayList<Integer> y, String color, Boolean fill) {
        super(0, 0, color, fill); // we are never to going to use Shape's x, y attribute so let's make it 0, 0 to emphasize that point
        this.len = x.size();
        this.x_prim = new int[len];
        this.y_prim = new int[len];
        for (int i = 0; i < len; i++) { // convert advanced ArrayList to primatives
            this.x_prim[i] = x.get(i);
            this.y_prim[i] = y.get(i);
            // System.out.println(this.x_prim[i]+ " | " + this.y_prim[i] + " *** " + x.get(i) + " | " + y.get(i));
        }
    }

    @Override
    public void draw(Graphics g) {
        if (g==null)
            System.out.println("drawing some sort of " + color + " triangle");
        else {
            // System.out.println(x + "" + y);

            g.setColor(color);

            // int len = x.size();
            // int x_prim[] = new int[len];
            // int y_prim[] = new int[len];

            for (int i = 0; i < len; i++) { // convert advanced ArrayList to primatives
                // x_prim[i] = x.indexOf(i);
                // y_prim[i] = y.indexOf(i);
                System.out.println(x_prim[i]+ " | " + y_prim[i]);
            }

            if (fill) {
                g.fillPolygon(x_prim, y_prim, len);
            }
            else {
                g.drawPolygon(x_prim, y_prim, len);
            }
        }
    }
}
