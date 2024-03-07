import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JFrame {

    private final JPanel panel;
    private final BufferedImage buffer;

    public Canvas() {
        setTitle("LineaBresenham");
        setSize(600, 600);
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(600, 600);
        setLocationRelativeTo(null);
        buffer = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_ARGB);
        add(panel);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        lineaBresenham(100, 100, 500, 500, Color.red);
        getGraphics().drawImage(buffer,0,0,panel);
    }

    public void lineaBresenham(int x1, int y1, int x2, int y2, Color a){
        int dy = y2 - y1;
        int dx = x2 - x1;
        int x = x1;
        int y = y1;
        int p;
        int incX = 1;
        int incY = 1;
        double m = (double) dy /dx;
        System.out.println("Pendiente: " + m);

        if (dy < 0){
            dy = -dy;
            incY = -1;
        }
        if (dx < 0){
            dx = -dx;
            incX = -1;
        }

        if (dx > dy){
            p = 2 * dy - dx;
            for (int i = 0; i <= dx; i++){
                if (p >= 0){
                    y += incY;
                    p += 2 * (dy - dx);
                }else{
                    p += 2 * dy;
                }
                x += incX;
                pixel(x, y, a);
            }
        }else{
            p = 2 * dx - dy;
            for (int i = 0; i <= dy; i++){
                if (p >= 0){
                    x += incX;
                    p += 2 * (dx - dy);
                }else{
                    p += 2 * dx;
                }
                y += incY;
                pixel(x, y, a);
            }
        }
    }

    private void pixel(int x, int y, Color a) {
        buffer.setRGB(x, y, a.getRGB());
    }
}
