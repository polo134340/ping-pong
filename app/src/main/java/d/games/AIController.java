package d.games;

public class AIController {

    public PlayerController playerController;
    public Rectangle ball;

    public AIController(PlayerController playerController, Rectangle ball){

        this.playerController =  playerController;
        this.ball = ball;

    }

    public void update(double dt){

        playerController.update(dt);

        if(ball.y < playerController.p_rect.y){
            playerController.moveUp(dt);
        }
        else if(ball.y + ball.height > playerController.p_rect.y + playerController.p_rect.height){
            playerController.moveDown(dt);
        }
    }
}


