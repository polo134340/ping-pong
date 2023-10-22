package d.games;
import java.awt.event.KeyEvent;

public class PlayerController{

    public Rectangle p_rect;
    public KL keyListener;

    public PlayerController(Rectangle p_rect, KL keyListener){
        this.p_rect = p_rect;
        this.keyListener = keyListener;

    }

    public PlayerController(Rectangle p_rect){

        this.p_rect = p_rect;
        this.keyListener =  null;
    }

   //if ai is controlling it, it wont have a keyListener (up, down keys)
    public void update(double dt){
        if(keyListener != null){
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
                moveDown(dt);
            }

            else if(keyListener.isKeyPressed(KeyEvent.VK_UP)){
                moveUp(dt);
            }
        }
    }

    public void moveUp(double dt){
        if(p_rect.y - Constants.PADDLE_SPEED * dt >  Constants.TOOLBAR_HEIGHT){
            this.p_rect.y -= (Constants.PADDLE_SPEED * dt);
        }
    }

    public void moveDown(double dt){
        if((p_rect.y + Constants.PADDLE_SPEED * dt) + p_rect.height < Constants.SCREEN_HEIGHT -  Constants.INSETS_BOTTOM){
            this.p_rect.y += (Constants.PADDLE_SPEED * dt);
        }
    }
}
