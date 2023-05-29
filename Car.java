import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Car {
    private int x;
    private int y;
    private boolean alive;
    private ImageIcon imageIcon;
    private Rectangle rectangle;
    private int speed = 6;

    public Car(int x, int y, String address) {
        imageIcon = new ImageIcon(address);
        this.x = x;
        this.y = y;
        this.alive = true;
        if(x<500){
            int scale=5;
        imageIcon=new ImageIcon(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth()/scale,imageIcon.getIconHeight()/scale,Image.SCALE_SMOOTH));
        }
        rectangle = new Rectangle(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void paint(Graphics graphics) {
        if (this.alive) {
            imageIcon.paintIcon(null, graphics, this.x, this.y);
        }
    }

    public void kill() {
        this.alive = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void resetLocation() {
        x = (int) (Window.WINDOW_WIDTH + rectangle.getWidth());
        Random random = new Random();
        int num = 1 + random.nextInt(3);
        if (num == 1) {
            y = Window.UP_ROAD;
        } else if (num == 2) {
            y = Window.MIDDLE_ROAD;
        } else if (num == 3) {
            y = Window.DOWN_ROAD;
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    public void moveUp(int roadLength) {
        if (y - roadLength > 0) {
            y -= roadLength;
        }
    }

    public boolean isStart() {
        return x <= 0;
    }

    public void moveDown(int roadLength) {
        if (y + rectangle.height + roadLength < Window.WINDOW_HEIGHT) {
            y += roadLength;
        }
    }

    public void moveLeft(int speed) {
        if (!isStart()) {
            x -= speed;
        }
    }

    public void moveRight(int speed) {
        if (x < Window.WINDOW_WIDTH - rectangle.width - 21) {
            x += speed;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return this.alive;
    }

}

