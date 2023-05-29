import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private ImageIcon icon=new ImageIcon("src\\images\\menu.jpg");
    Background(int width,int height){
        icon=new ImageIcon(icon.getImage().getScaledInstance(width, height,Image.SCALE_SMOOTH));

        this.setSize(width,height);
        this.setLayout(null);
        this.setLocation(0,0);
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        icon.paintIcon(null,graphics,0,0);
    }
}
