package d.games;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class ML extends MouseAdapter implements MouseMotionListener{

    public double x = 0.0, y = 0.0;
    public boolean isPressed = false;

    @Override
    public void mousePressed(MouseEvent e){
        isPressed = true;

    }


    @Override
    public void mouseReleased(MouseEvent e){
        isPressed = false;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

    }

    public double getMouseX(){
        return this.x;
        
    }


    public double getMouseY(){

        return this.y;
    }

    public boolean isMousePressed(){

        return this.isPressed;
    }
}



