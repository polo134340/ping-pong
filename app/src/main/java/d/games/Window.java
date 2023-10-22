package d.games;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;

import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{

    Graphics2D g2;
    PlayerController p_Controller;
    AIController ai_Controller;
    KL keyListener = new KL();
    Rectangle playerOne, ai, ballRect;
    Rectangle rectangle, vertical1, vertical2, vertical3, vertical4, vertical5;
    Rectangle horizontal1, horizontal2, horizontal3, horizontal4, horizontal5;
    Ball ball;
    Text leftScoreText, rightScoreText;
    boolean isRunning = true;

    public Window(){

        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.addKeyListener(keyListener);


        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM  = this.getInsets().bottom;

        g2 = (Graphics2D)this.getGraphics(); 

        leftScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.TEXT_X_POS, Constants.TEXT_Y_POS);
        rightScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH - Constants.TEXT_X_POS - 7 , Constants.TEXT_Y_POS);
        
        playerOne = new Rectangle(Constants.HZ_PADDING, Constants.V_PADDING,  Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PLAYER_PADDLE_COLOUR);
        p_Controller = new PlayerController(playerOne, keyListener);

        ai = new Rectangle(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING, Constants.V_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOUR);
        ballRect = new Rectangle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 , Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Constants.BALL_COLOUR);
        ball = new Ball(ballRect, playerOne, ai, leftScoreText, rightScoreText);

        ai_Controller = new AIController(new PlayerController(ai), ballRect);
    
    }



    public void update(double dt){
        //time taken to complete last frame
        //System.out.println("" + dt + " seconds passed since the last frame.");
        //System.out.println(1/dt + " fps");

        Image dbImage =  createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg, 14);
        g2.drawImage(dbImage, 0, 0, this);
        
        
        p_Controller.update(dt);
        ai_Controller.update(dt);
        ball.update(dt);

        /*if (keyListener.isKeyPressed(KeyEvent.VK_W)){
            System.out.println("The user is pressing the W key");

        }
        else if (keyListener.isKeyPressed(KeyEvent.VK_S)){
            
            System.out.println("The user is pressing the S key");
        }*/
    }

    public void draw(Graphics g, int i){

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        rectangle = new Rectangle(50, 60, 500, 300, Color.GREEN);

        vertical1 = new Rectangle(298, 60, 4, 300, Color.DARK_GRAY);
        vertical2 = new Rectangle(70, 80, 2, 260, Color.WHITE);
        vertical3 = new Rectangle(530, 80, 2, 260, Color.WHITE);
        vertical4 = new Rectangle(185, 105, 2, 210, Color.WHITE);
        vertical5 = new Rectangle(415, 105, 2, 210, Color.WHITE);
        
        horizontal1 = new Rectangle(70, 80, 460, 2, Color.WHITE);
        horizontal2 = new Rectangle(70, 105, 460, 2, Color.WHITE);        
        horizontal3 = new Rectangle(70, 315, 460, 2, Color.WHITE);
        horizontal4 = new Rectangle(70, 340, 460, 2, Color.WHITE);
        horizontal5 = new Rectangle(185, 209, 230, 2, Color.WHITE);

        //Chronological call stack
        rectangle.draw(g2);

        vertical2.draw(g2);
        vertical3.draw(g2);
        vertical4.draw(g2);
        vertical5.draw(g2);

        horizontal1.draw(g2);
        horizontal2.draw(g2);
        horizontal3.draw(g2);
        horizontal4.draw(g2);
        horizontal5.draw(g2);

        vertical1.draw(g2);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);

        ai.draw(g2);
        playerOne.draw(g2);
        ballRect.draw(g2); 
    }

    public void run(){
        double lastFrameTime = 0.0;

        while(isRunning){
            //main movement of snake
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }

        this.dispose();
        return;
    }   

    public void stop(){

        isRunning = false;

    }
}
