package d.games;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.text.CollationElementIterator;
import java.awt.*;

import javax.print.attribute.standard.MediaSize.Engineering;
import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

public class MainMenu extends JFrame implements Runnable{

    Graphics2D g2;
    KL keyListener = new KL();
    ML mouseListener =  new ML();
    public Text startGame, endGame, instructions, instructions2, pingpong;
    public boolean isRunning = true;
    

    public MainMenu(){

        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        
        this.pingpong = new Text("Ping Pong", new Font("Times New Roman", Font.BOLD,40), Constants.SCREEN_WIDTH/2 - 87.5, Constants.SCREEN_HEIGHT / 3  - 30, Color.WHITE);

        this.startGame =  new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH/2 -190 , Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
        this.endGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH/2 + 110, Constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
        
        this.instructions =  new Text("You are the blue player, the first to 10 against the computer,", new Font("Times New Roman", Font.BOLD, 20), Constants.SCREEN_WIDTH/2 - 251, Constants.SCREEN_HEIGHT/2.0 - 40, Color.WHITE);
        this.instructions2 =  new Text("wins the game of ping-pong", new Font("Times New Roman", Font.BOLD, 20), Constants.SCREEN_WIDTH/2 - 120, Constants.SCREEN_HEIGHT/2.0 - 10, Color.WHITE);
        g2 = (Graphics2D)getGraphics();

    }



    public void update(double dt){
        //time taken to complete last frame
        //System.out.println("" + dt + " seconds passed since the last frame.");
        //System.out.println(1/dt + " fps");

        Image dbImage =  createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg, 14);
        g2.drawImage(dbImage, 0, 0, this);

        if(mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width / 2
            && mouseListener.getMouseY() > startGame.y - startGame.height / 2 && mouseListener.getMouseY() < startGame.y + startGame.height / 2){

            startGame.colour = Color.GREEN;
                if(mouseListener.isMousePressed()){
                    Main.changeState(1);
                }
        }

        else{
            startGame.colour = Color.WHITE;
        }

        if(mouseListener.getMouseX() > endGame.x && mouseListener.getMouseX() < endGame.x + endGame.width / 2
            && mouseListener.getMouseY() > endGame.y - endGame.height / 2 && mouseListener.getMouseY() < endGame.y + endGame.height / 2){
                endGame.colour = Color.GREEN;

                if(mouseListener.isMousePressed()){
                    Main.changeState(2);
                }
        }

        else{
            endGame.colour = Color.WHITE;
        }
    }

    public void draw(Graphics g, int i){

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        pingpong.draw(g2);
        instructions.draw(g2);
        instructions2.draw(g2);
        startGame.draw(g2);
        endGame.draw(g2);
    }


    public void stop(){
        isRunning = false;  
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

        this.dispose();// disposes of open window
        return;
    }   
}

