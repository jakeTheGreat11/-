import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {
    boolean show=true;
    StartWindow(){
        this.setResizable(false);
        this.setSize(550,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        new Thread(()->{
            while (true){
                Utils.playMusic("src\\sounds\\menuSound.wav");
                try {
                    Thread.sleep(89629);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Background background=new Background(this.getWidth(),this.getHeight());
        int buttonWidth=100;
        int buttonHeight=50;
        JButton start=new JButton("start");
        JButton rules=new JButton("Rules");
        start.setFont(new Font("Arial", Font.BOLD, 20));
        start.setForeground(Color.WHITE);
        start.setBackground(new Color(200, 35, 40));
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        rules.setFont(new Font("Arial", Font.BOLD, 20));
        rules.setForeground(Color.WHITE);
        rules.setBackground(new Color(60, 40, 210));
        rules.setFocusPainted(false);
        rules.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        start.setSize(buttonWidth,buttonHeight);
        rules.setSize(buttonWidth,buttonHeight);
        start.setLocation(this.getWidth()/2-start.getWidth()/2,this.getHeight()/2-2*buttonHeight);
        rules.setLocation(5,7);
        JLabel label=new JLabel("<html>Rules:\n<html>" +
                "Use the directional arrows to move the car to the right or left.\n" +
                "The player must avoid the moving cars on the road without colliding with them.\n" +
                "If the player collides with a car, the game ends.\n" +
                "The player must collect as many coins as possible.\n" +
                "The game continues as long as the player avoids the cars and travels the road.\n" +
                "If the player travels a certain distance without colliding with the cars, the cars move faster and more frequently on the road.\n" +
                "There will also be different types of vehicles on the road that the player must avoid.\n" +
                "The final score is calculated based on the number of coins collected by the player without colliding with the cars on the road.");
        label.setForeground(Color.white);
        label.setBounds(75,170,400,250);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setVisible(false);
        this.add(label);
        start.addActionListener((e)->{
            Window window=new Window();
            this.setVisible(false);
        });
        rules.addActionListener((e)->{
            start.setVisible(!show);
            label.setVisible(show);
            show=!show;
        });
        this.add(background);
        this.add(start);
        this.add(rules);
        this.add(background);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        StartWindow startWindow=new StartWindow();
    }
}