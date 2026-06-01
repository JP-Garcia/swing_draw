// import java.awt.Component;
// import java.awt.List;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
// import java.io.Serializable;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

import shapes.*;

import static java.lang.Math.abs;
// import static java.lang.Math.round;



public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener {

    protected enum ShapeType {
        LINE, RECTANGLE, SQUARE, TRIANGLE, CUSTOM_TRIANGLE, OVAL, CIRCLE, POLYGON
    }


    protected ArrayList<Shape> shapes = new ArrayList<>();
    protected ShapeType currentShape = ShapeType.POLYGON;
    protected String currentColor = "#ff0000";
    protected Boolean fill = false;
    protected String place = "stamp";
    protected int gridSize = 20;

    public PaintPanel() {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("repainting window....");
        int w = this.getWidth();
        int h = this.getHeight();

        for (int i = 0; i < w; i = i+gridSize) { // vertical grid
            g.setColor(Color.decode("#CCCCCC"));
            g.drawLine(i, 0, i, h);
        }
        for (int i = 0; i < h; i = i+gridSize) { // horizontal grid
            g.setColor(Color.decode("#CCCCCC"));
            g.drawLine(0, i, w, i);
        }
        for (Shape s : shapes) {
            s.draw(g);
        }
    }

    public void clearLastShape() {
        int size = shapes.size();
        if(size > 0) {
            shapes.remove(size - 1);
        }
        repaint();
    }

    Boolean firstShape = true;
    int x_i = 0;
    int y_i = 0;

    public int calculateSideLength(int width, int height) {
        if (Math.abs(width) > Math.abs(height)) {return abs(width);}
        else {return abs(height);}
    }
    public int larger(int i_1, int i_2) {
        if (i_1 > i_2) {return i_1;}
        else {return i_2;}
    }
    public int smaller(int i_1, int i_2) {
        if (i_1 < i_2) {return i_1;}
        else {return i_2;}
    }

    int[] coord = {0, 0, 0, 0, 0, 0};
    int indexT = 1;
    public int exit;
    public void triangleTracker(int x, int y) {
        if (indexT == 1){
            coord[0] = x;
            coord[1] = y;
        }
        else if (indexT == 2){
            coord[2] = x;
            coord[3] = y;
        }
        else if (indexT == 3){
            coord[4] = x;
            coord[5] = y;
            for (int i = 0; i < coord.length; i++) {
                System.out.print(" " + coord[i]);
            }
        }
        else System.out.println("Error, index out of bounds");
    }


    int limit = 8;
    ArrayList<Integer> x_lis = new ArrayList<Integer>();
    ArrayList<Integer> y_lis = new ArrayList<Integer>();
    public void polygonTracker(int x, int y) {
        if (indexT>1) {
            if (x == x_lis.get(0) && y==y_lis.get(0)){
                indexT = limit;
            }
        }
        x_lis.add(x);
        y_lis.add(y);
        
        //     for (int i = 0; i < coord.length; i++) {
        //         System.out.print(" " + coord[i]);
        //     }
        // }
        // else System.out.println("Error, index out of bounds");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // handled in mouseClicked
        System.out.println("MousePressed");
        x_i = e.getX();
        x_i = Math.round((float)x_i/(float)gridSize) * gridSize;
        y_i = e.getY();
        y_i = Math.round((float)y_i/(float)gridSize) * gridSize;
        firstShape = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) { // Comments indicate how shape is placed in relation to mouse
        int mouesX = e.getX();
        mouesX = Math.round((float)mouesX/(float)gridSize) * gridSize;
        int mouesY = e.getY();
        mouesY = Math.round((float)mouesY/(float)gridSize) * gridSize;
        int width = 40;
        int height = 20;
        // int midWid = width / 2;
        // int midWid = height / 2;
        int sideLength = calculateSideLength(width, height);
        Shape s;
        if (currentShape == ShapeType.LINE) {
            s = new Line(mouesX, mouesY, currentColor, fill); // Top-left
        }
        else if (currentShape == ShapeType.RECTANGLE) {
            s = new Rectangle(mouesX, mouesY, width, height, currentColor, fill); // Top-left
        }
        else if (currentShape == ShapeType.SQUARE) {
            s = new Square(mouesX, mouesY, width, height, currentColor, fill); // Top-left
        }
        else if (currentShape == ShapeType.OVAL) {
            s = new Oval(mouesX - width, mouesY - height, width, height, currentColor, fill); // Middle
        }
        else if (currentShape == ShapeType.CIRCLE) {
            s = new Circle(mouesX - sideLength, mouesY - sideLength, sideLength, currentColor, fill); // Middle
        }
        else if (currentShape == ShapeType.TRIANGLE) {
            s = new Triangle(mouesX - width, mouesY + height, mouesX, mouesY - height, mouesX + width, mouesY + height, currentColor, fill); // Middle
        }
        else if (currentShape == ShapeType.CUSTOM_TRIANGLE) {
            if (indexT <= 3) {
                triangleTracker(mouesX, mouesY);
                System.out.println("Got coord "+ indexT);
                s = new Rectangle(mouesX - 1, mouesY - 1, 3, 3, currentColor, false);
                if (indexT == 3) {
                    clearLastShape();
                    clearLastShape();
                    s = new Triangle(coord[0], coord[1], coord[2], coord[3], coord[4], coord[5], currentColor, fill); // Middle
                    indexT = 0;
                }
                indexT++;
            }
            else {
                System.out.println("Error, index out of bounds");
                s = new Rectangle(mouesX - 4, mouesY - 4, 9, 9, currentColor, fill);
                // indexT = 0;
            }
        }
        else if (currentShape == ShapeType.POLYGON) {
            if (indexT <= limit) {
                polygonTracker(mouesX, mouesY);
                System.out.println("Got coord "+ indexT +": x,y="+x_lis+y_lis);
                s = new Rectangle(mouesX - 1, mouesY - 1, 3, 3, currentColor, false);
                if ((indexT == limit)) {
                    for (int i = 0; i < y_lis.size()-1; i++) {
                        clearLastShape();
                    } 
                    s = new Poly(x_lis, y_lis, currentColor, fill); // Middle
                    indexT = 0;
                    x_lis.clear();
                    y_lis.clear();
                    System.out.println("AMOG");
                }
                indexT++;
            }
            else {
                System.out.println("Error: index out of polygon bounds");
                s = new Rectangle(mouesX - 4, mouesY - 4, 9, 9, currentColor, fill);
                // indexT = 0;
            }
        }
        else s = new Rectangle(mouesX, mouesY, width, height, currentColor, fill);
        shapes.add(s);
        s.draw(getGraphics());
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // handled in mouseClicked
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // ignored
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // ignored
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // int screenW = this.getWidth();
        // int screenH = this.getHeight();
        System.out.println("mouseDragged");
        if (place == "brush") {
            mouseClicked(e);
        }
        else {
            int mouesX = e.getX();
            mouesX = Math.round((float)mouesX/(float)gridSize) * gridSize;
            int mouesY = e.getY();
            mouesY = Math.round((float)mouesY/(float)gridSize) * gridSize;
            int width = mouesX - x_i;
            int height = mouesY - y_i;
            // int width_mid = width / 2;
            // int width_mid = height / 2;
            // int sideLength = calculateSideLength(width, height);
            System.out.println(x_i + " " + y_i + "|" + mouesX + " " + mouesY);

            if (firstShape == true) {
                System.out.println("firstShape");
                firstShape = false;
            }
            else {
                System.out.print("looping");
                clearLastShape();
            }
            Shape s;
            if (currentShape == ShapeType.LINE) {
                s = new Line(x_i, y_i, mouesX, mouesY, currentColor, fill); // Top-left
            }
            else if (currentShape == ShapeType.RECTANGLE) {
                s = new Rectangle(x_i, y_i, width, height, currentColor, fill); // Top-left
            }
            else if (currentShape == ShapeType.SQUARE) {
                s = new Square(x_i, y_i, width, height, currentColor, fill); // Top-right
            }
            else if (currentShape == ShapeType.OVAL) {
                s = new Oval(x_i, y_i, width, height, currentColor, fill); // Middle
            }
            else if (currentShape == ShapeType.CIRCLE){
                s = new Circle(x_i, y_i, width, height, currentColor, fill); // Middle
            }
            else if ((currentShape == ShapeType.TRIANGLE) || (currentShape == ShapeType.CUSTOM_TRIANGLE)) {
                s = new Triangle(x_i - width, y_i + height, x_i, y_i - height, x_i + width, y_i + height, currentColor, fill); // Middle
            }
            else s = new Rectangle(x_i, y_i, width, height, currentColor, fill);
            shapes.add(s);
            s.draw(getGraphics());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Ignored  b  
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // For loop to change width + height, manually call repaint?
        // repaint();
        // System.out.println("manually repainted");
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
    }  
}
