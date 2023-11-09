import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorHelp {
    private Map<String, Color> colorMap = new HashMap<String, Color>();
    public ColorHelp() {
        colorMap.put("black", Color.black);
        colorMap.put("blue", Color.blue);
        colorMap.put("red", Color.red);
        colorMap.put("yellow", Color.yellow);
        colorMap.put("green", Color.green);
        colorMap.put("white", Color.white);
        colorMap.put("special", new Color(25, 125, 250));
    }
    public Color setColor(String color) {
        Color col2 = colorMap.get(color);
        System.out.println(col2);
        return col2;
    }
    // Example:
    public static void main(String[] args) {
        ColorHelp finder = new ColorHelp();
        finder.setColor("red");
    }
}