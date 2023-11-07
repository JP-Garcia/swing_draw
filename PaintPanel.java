import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import shapes.*;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener, Serializable {

    protected enum ShapeType {
        RECTANGLE, SQUARE, TRIANGLE, CUSTOM_TRIANGLE, OVAL, CIRCLE
    }


    protected ArrayList<Shape> shapes = new ArrayList<>();
    protected ShapeType currentShape = ShapeType.TRIANGLE;
    protected String currentColor = "#ff0000";

    public PaintPanel() {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("painting....");
        int w = this.getWidth();
        int h = this.getHeight();
        int eye = 30;
        int eyeSpacing = 30;
        int centerX = w/2;
        int centerY = h/2;
        int thirdY = h/3;
        int nose = 40;
        int mouth = 120;

        // draw the face (red)
        g.setColor(Color.RED);
        g.drawOval(10,10,w-20,h-20);

        // draw the eyes (blue rectangles)
        g.setColor(Color.BLUE);
        g.drawRect(centerX-eyeSpacing-eye,thirdY-eye,eye,eye);
        g.drawRect(centerX+eyeSpacing,thirdY-eye,eye,eye);

        // draw the nose (purple triangle)
        g.setColor(Color.MAGENTA);
        g.drawLine(centerX, centerY, centerX + nose/2, centerY + nose/2);
        g.drawLine(centerX + nose/2, centerY + nose/2, centerX - nose/2, centerY + nose/2);
        g.drawLine(centerX-nose/2, centerY+nose/2, centerX, centerY);

        // draw the mouth (black oval ... partially hidden)        
        g.setColor(Color.BLACK);
        g.drawOval(centerX-mouth/2, h-thirdY, mouth, nose);

        // hide the top part of the mouth to make it look like a smile
        g.setColor(Color.WHITE);
        g.fillRect(centerX-mouth/2, h-thirdY-20, mouth, 40);


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
        if (Math.abs(width) > Math.abs(height)) {
            return width;
        }
        return height;
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

    @Override
    public void mousePressed(MouseEvent e) {
        // handled in mouseClicked
        System.out.println("MousePressed");
        x_i = e.getX();
        y_i = e.getY();
        firstShape = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) { // Comments indicate how shape is placed in relation to mouse
        // int mouesX = e.getX();
        // int mouesY = e.getY();
        int width = 40;
        int height = 20;
        // int midWid = width / 2;
        // int midWid = height / 2;
        int sideLength = calculateSideLength(width, height);
        Shape s;
        if (currentShape == ShapeType.RECTANGLE) {
            s = new Rectangle(x_i, y_i, width, height, currentColor); // Top-left
        }
        else if (currentShape == ShapeType.SQUARE) {
            s = new Square(x_i, y_i, width, height, currentColor); // Top-left
        } 
        else if (currentShape == ShapeType.OVAL) {
            s = new Oval(x_i, y_i, width, height, currentColor); // Middle
        } 
        else if (currentShape == ShapeType.CIRCLE) {
            s = new Circle(x_i - sideLength, y_i - sideLength, sideLength, currentColor); // Middle
        } 
        else if (currentShape == ShapeType.TRIANGLE) {
            s = new Triangle(x_i - width, y_i + height, x_i, y_i - height, x_i + width, y_i + height, currentColor); // Middle
        }
        else if (currentShape == ShapeType.CUSTOM_TRIANGLE) {
            if (indexT <= 3) {
                triangleTracker(x_i, y_i);
                System.out.println("Got coord "+ indexT);
                s = new Rectangle(x_i - 1, y_i - 1, 3, 3, currentColor);
                if (indexT == 3) {
                    clearLastShape();
                    clearLastShape();
                    s = new Triangle(coord[0], coord[1], coord[2], coord[3], coord[4], coord[5], currentColor); // Middle
                    indexT = 0;
                }
                indexT++;
            }
            else {
                System.out.println("Error, index out of bounds");
                s = new Rectangle(x_i - 4, y_i - 4, 9, 9, currentColor);
                // indexT = 0;
            }
        }
        else s = new Rectangle(x_i, y_i, width, height, currentColor);
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
        int mouesX = e.getX();
        int mouesY = e.getY();
        int width = mouesX - x_i;
        int height = mouesY - y_i;
        // int width_mid = width / 2;
        // int width_mid = height / 2;
        int sideLength = calculateSideLength(width, height);
        System.out.println(mouesX + " " + mouesY);

        if (firstShape == true) {
            System.out.println("firstShape");
            firstShape = false;
        }
        else {
            System.out.print("looping");
            clearLastShape();
        }
        Shape s;
        if (currentShape == ShapeType.RECTANGLE) {
            s = new Rectangle(x_i, y_i, width, height, currentColor); // Top-left
        }
        else if (currentShape == ShapeType.SQUARE) {
            s = new Square(x_i, y_i, width, height, currentColor); // Top-right
        } 
        else if (currentShape == ShapeType.OVAL) {
            s = new Oval(x_i - width, y_i - height, width * 2, height * 2, currentColor); // Middle
        } 
        else if (currentShape == ShapeType.CIRCLE){
            s = new Circle(x_i - sideLength, y_i - sideLength, sideLength, currentColor); // Middle
        }  
        else if ((currentShape == ShapeType.TRIANGLE) || (currentShape == ShapeType.CUSTOM_TRIANGLE)) {
            s = new Triangle(x_i - width, y_i + height, x_i, y_i - height, x_i + width, y_i + height, currentColor); // Middle
        }
        else s = new Rectangle(x_i, y_i, width, height, currentColor);
        shapes.add(s);
        s.draw(getGraphics());
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
