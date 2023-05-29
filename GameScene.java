import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class GameScene extends JPanel{
    private Car player;
    private ImageIcon road = new ImageIcon("src\\images\\road.png");
    private int currection=10;
    private int firstRoadsX=0;
    private int secondRoadsX;
    private LinkedList <Car> cars;
    private Coin coin1 =new Coin(Window.WINDOW_WIDTH+100,68);
    private Coin coin2 =new Coin(Window.WINDOW_WIDTH,68+Window.ROAD_LENGTH);
    private Coin coin3 =new Coin(Window.WINDOW_WIDTH+50,68+2*Window.ROAD_LENGTH);
    private int moveCars=0;
    private double speed;
    private double addSpeed;
    private int score=0;
    private boolean gameOver=false;
    JLabel labelScore=new JLabel("Your score is "+score);
    public GameScene(){
        makeGame();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void makeGame(){
        this.setBackground(new Color(70,70,70));
        this.player = new Car(100,Window.MIDDLE_ROAD,"src\\images\\car1.png");
        labelScore.setFont(new Font("Arial", Font.BOLD, 15));
        cars=new LinkedList<>();
        secondRoadsX=-road.getIconWidth();
        speed=4;
        addSpeed=0.1;
        Car car1=new Car(800,Window.UP_ROAD,randomCar());
        Car car2=new Car(950,Window.MIDDLE_ROAD,randomCar());
        labelScore.setForeground(Color.white);
        this.add(labelScore);
        car2.setSpeed(8);
        cars.push(car1);
        cars.push(car2);
        new Thread(()->{
            gameOver=false;
            while (!gameOver){
                moving();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sortRoads();
                repaint();
            }
        }).start();
        new Thread(()->{
            while (!gameOver){
            Utils.playMusic("src\\sounds\\engine.wav");
                try {
                    Thread.sleep(485);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public String randomCar(){
        Random random=new Random();
        int r=random.nextInt(7);
        return "src\\images\\OCAR"+r+".jpg";
    }
    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        road.paintIcon(null,graphics,firstRoadsX,-currection);
        road.paintIcon(null,graphics,firstRoadsX,road.getIconHeight()-currection);
        road.paintIcon(null,graphics,firstRoadsX,2*road.getIconHeight()-currection);
        road.paintIcon(null,graphics,secondRoadsX,-currection);
        road.paintIcon(null,graphics,secondRoadsX,road.getIconHeight()-currection);
        road.paintIcon(null,graphics,secondRoadsX,2*road.getIconHeight()-currection);
        coin1.paint(graphics);
        coin2.paint(graphics);
        coin3.paint(graphics);
        for (Car car:cars) {
         car.paint(graphics);
        }
        this.player.paint(graphics);

    }
    public boolean collision(){
        return (Utils.checkCollision(player.getRectangle(), cars.get(0).getRectangle()) ||
                Utils.checkCollision(player.getRectangle(), cars.get(1).getRectangle()));
    }
    private void getCoin(Coin coin){
        if(player.getRectangle().intersects(coin.getRectangle())){
            Utils.playMusic("src\\sounds\\coinTake.wav");
            score++;
            labelScore.setText("Your score is "+score);
            coin.resetCoin();
        }
    }
    public void moving() {
        if (!collision()){
            getCoin(coin1);
            getCoin(coin2);
            getCoin(coin3);
            coin1.move(speed);
            coin2.move(speed);
            coin3.move(speed);
        if (firstRoadsX <= -road.getIconWidth()) {
            firstRoadsX = road.getIconWidth();
        }
        if (secondRoadsX <= -road.getIconWidth()) {
            secondRoadsX = road.getIconWidth();
        }
        if (moveCars == 0) {
                for (Car car : cars) {
                    car.moveLeft(car.getSpeed());
                    if (car.isStart()) {
                        car.resetLocation();
                        Random random = new Random();
                        car.setSpeed((int) (speed + random.nextInt((int) (speed))));
                        car.setImage(new ImageIcon(randomCar()));
                        speed+=addSpeed;
//                        score++;
//                        labelScore.setText("Your score is "+score);
                    }
                }
                while (cars.get(0).getY()==cars.get(1).getY()){
                    if(cars.get(0).getX()>cars.get(1).getX()){
                        cars.get(0).resetLocation();
                    }else {
                        cars.get(1).resetLocation();
                    }
                }
            moveCars = 4;
        }
        moveCars--;
        firstRoadsX-=speed;
        secondRoadsX-=speed;
    }else {
            gameOver=true;
            Utils.playMusic("src\\sounds\\boom.wav");
        }
    }
public void sortRoads(){
    if(firstRoadsX<secondRoadsX){
        secondRoadsX=firstRoadsX+road.getIconWidth();
    }else {
        firstRoadsX=secondRoadsX+road.getIconWidth();
    }
}
    public Car getPlayer() {
        return player;
    }
}
