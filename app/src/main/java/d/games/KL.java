package d.games;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {

    private boolean[] KeyPressed = new boolean[128];

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        
        KeyPressed[keyEvent.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        
        KeyPressed[keyEvent.getKeyCode()] = false;
        
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    
        
    }
    
    public boolean isKeyPressed(int keyCode){

        return KeyPressed[keyCode];

    }

}
