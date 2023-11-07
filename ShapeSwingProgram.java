// import java.awt.event.MouseEvent;
// import java.awt.geom.RoundRectangle2D;
// import java.awt.FlowLayout;
// import java.awt.Graphics;
// import java.awt.GridBagLayout;
// import java.awt.LinearGradientPaint;
// import java.awt.Point;
// import java.awt.PopupMenu;
// import java.awt.RenderingHints;
// import java.awt.Window;
// import java.awt.Desktop;
// import javax.lang.model.util.Elements.Origin;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JWindow;
// import javax.swing.Timer;
// import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.event.MenuKeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class ShapeSwingProgram extends JFrame implements ActionListener {



    private PaintPanel paintPanel = new PaintPanel();

    public ShapeSwingProgram(String title) {
        super(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

/*
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton buttonUndo = new JButton("undo");
        buttonUndo.addActionListener(this);
        buttonPanel.add(buttonUndo);
        JButton buttonRefresh = new JButton("refresh");
        buttonRefresh.addActionListener(this);
        buttonPanel.add(buttonRefresh);
        this.add(buttonPanel, BorderLayout.PAGE_START);
*/


        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;



        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(MenuKeyEvent.VK_F);
        menuBar.add(menu);

        //a group of file-related menu items
        menuItem = new JMenuItem("Open", MenuKeyEvent.VK_O);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Save", MenuKeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //menuItem = new JMenuItem("Save As...", MenuKeyEvent.VK_A);
        //menuItem.addActionListener(this);
        //menu.add(menuItem);
        submenu = new JMenu("Save As...");
        menuItem = new JMenuItem("PNG");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menuItem = new JMenuItem("JPEG");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menuItem = new JMenuItem("Bitmap");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        menuItem = new JMenuItem("Exit", MenuKeyEvent.VK_X);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //Build our shape menu
        menu = new JMenu("Shape");

        ButtonGroup group2 = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("Rectangle");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Square");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Oval");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Circle");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Triangle");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Custom Triangle");
        rbMenuItem.addActionListener(this);
        group2.add(rbMenuItem);
        menu.add(rbMenuItem);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // -- CHANGED color into a separate menu by JP

        // menu.addSeparator();
        // submenu = new JMenu("Color");
        menu = new JMenu("Color");

        // create a group so you can only select one color at a time
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("Red");
        rbMenuItem.setBackground(Color.RED);
        rbMenuItem.addActionListener(this);
        rbMenuItem.setSelected(true); // copy to rectangle?
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Yellow");
        rbMenuItem.setBackground(Color.YELLOW);
        rbMenuItem.addActionListener(this);
        rbMenuItem.setSelected(true); // copy to rectangle?
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Green");
        rbMenuItem.setBackground(Color.GREEN);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Blue");
        rbMenuItem.setBackground(Color.BLUE);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Black");
        rbMenuItem.setBackground(Color.BLACK);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("White");
        rbMenuItem.setBackground(Color.WHITE);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        // submenu.add(rbMenuItem);
        menu.add(rbMenuItem);

        // menu.add(submenu);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);


        /*
        menu = new JMenu("Paint Type");

        ButtonGroup group3 = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("Outline");
        rbMenuItem.addActionListener(this);
        group3.add(rbMenuItem);
        menu.add(rbMenuItem);
        
        rbMenuItem = new JRadioButtonMenuItem("Fill");
        rbMenuItem.addActionListener(this);
        group3.add(rbMenuItem);
        menu.add(rbMenuItem);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        */


        paintPanel.setPreferredSize(new Dimension(500,500));
        paintPanel.setBackground(Color.WHITE);
        this.getContentPane().add(paintPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ShapeSwingProgram paintProgram = new ShapeSwingProgram("John Paul's Shape Painting Program");
        paintProgram.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        paintPanel.invalidate();
//        paintPanel.repaint();
        //System.out.println

        if (e.getActionCommand() == "Rectangle"){
            paintPanel.currentShape = PaintPanel.ShapeType.RECTANGLE;
        }
        else if (e.getActionCommand() == "Square"){
            paintPanel.currentShape = PaintPanel.ShapeType.SQUARE;
        }
        else if (e.getActionCommand() == "Oval"){
            paintPanel.currentShape = PaintPanel.ShapeType.OVAL;
        }
        else if (e.getActionCommand() == "Circle"){
            paintPanel.currentShape = PaintPanel.ShapeType.CIRCLE;
        }
        else if (e.getActionCommand() == "Triangle"){
            paintPanel.currentShape = PaintPanel.ShapeType.TRIANGLE;
        }
        else if (e.getActionCommand() == "Custom Triangle"){
            paintPanel.currentShape = PaintPanel.ShapeType.CUSTOM_TRIANGLE;
        }
        if (e.getActionCommand() == "Red") {
            paintPanel.currentColor = "#ff0000";
        }
        if (e.getActionCommand() == "Yellow") {
            paintPanel.currentColor = "#ffff00";
        }
        if (e.getActionCommand() == "Green") {
            paintPanel.currentColor = "#00ff00";
        }
        if (e.getActionCommand() == "Blue") {
            paintPanel.currentColor = "#0000ff";
        }
        if (e.getActionCommand() == "Black") {
            paintPanel.currentColor = "#000000";
        }
        if (e.getActionCommand() == "White") {
            paintPanel.currentColor = "#ffffff";
        }
        if (e.getActionCommand() == "Save") {
            save("png");
        }
        if (e.getActionCommand() == "PNG") {
            save("png");
        }
        if (e.getActionCommand() == "JPEG") {
            save("jpg");
        }
        if (e.getActionCommand() == "Bitmap") {
            save("bmp");
        }
        if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }
    }

    public void save(String formatName) {
        BufferedImage bImg = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        paintPanel.paintAll(cg);
        try {
                if (ImageIO.write(bImg, formatName, new File("./output_image."+formatName)))
                {
                    System.out.println("-- saved as "+formatName);
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
}