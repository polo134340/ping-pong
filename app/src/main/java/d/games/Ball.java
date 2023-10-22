package d.games;

public class Ball {

    public Rectangle rectangle;
    public Rectangle leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;
    //velocity in x, y

    private double vy =  360.0;
    private double vx = -180.0;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle, Text leftScoreText, Text rightScoreText){

        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle =  rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;

    }

    public double calculateVelocityAngle(Rectangle paddle){

        double relativeIntersect_Y = (paddle.y + (paddle.height / 2.0 )) - (this.rectangle.y + (this.rectangle.height / 2.0 ));
        double normalIntersect_Y = (relativeIntersect_Y / (paddle.height / 2.0));
        double theta = normalIntersect_Y * Constants.MAX_ANGLE;
        return Math.toRadians(theta);

    }


    //16.03 minutes

    public void update(double dt) {

        if(vx < 0.0){
            //if to left and in height range of the paddle, then bounce ball
            if(this.rectangle.x + (vx * dt) < this.leftPaddle.x + this.leftPaddle.width &&
                this.rectangle.y + (vy * dt) > this.leftPaddle.y && this.rectangle.y + (vy * dt) < this.leftPaddle.y + this.leftPaddle.height){
                
                double theta = calculateVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED; 

                double oldSign = Math.signum(vx);

                this.vy = newVy;
                this.vx = newVx * (- 1.0 * oldSign);
            }
        //player loses point
        }

        else if(vx >= 0.0){
            if(this.rectangle.x + this.rectangle.width + (vx * dt) >= this.rightPaddle.x  && 
                this.rectangle.y + (vy * dt) > this.rightPaddle.y && this.rectangle.y + (vy * dt) + this.rectangle.height < this.rightPaddle.y + this.rightPaddle.height){
                    double theta = calculateVelocityAngle(rightPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED; 
    
                    double oldSign = Math.signum(vx);
    
                    this.vy = newVy;
                    this.vx = newVx * (- 1.0 * oldSign);
            }
        
        }
        
        if(vy >= 0.0){

            if (this.rectangle.y + this.rectangle.height + (vy * dt) > Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM){
                this.vy *= - 1;
            }
        }
//15.57 minutes add velocity * dt to make speed after touching paddle the same.
        else if(vy < 0.0){
            if(this.rectangle.y + (vy * dt) < Constants.TOOLBAR_HEIGHT){
                this.vy *= -1;
            }
        }
        //position = position + velocity
        //velocity = velocity + acceleration
        this.rectangle.x += vx * dt;
        this.rectangle.y += vy * dt;

        if (this.rectangle.x + this.rectangle.width < leftPaddle.x){
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore ++;
            rightScoreText.text = "" + rightScore; //casts to string

            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;

            this.vy = 360.0;
            this.vx = 180.0;

            if(rightScore >= Constants.WINNING_SCORE){
                rightScoreText.text = "0";
                Main.changeState(2);
                
            }

        }

        else if(this.rectangle.x > rightPaddle.x + rightPaddle.width){
            int leftScore = Integer.parseInt(rightScoreText.text);
            leftScore ++;
            leftScoreText.text = "" + leftScore; //casts to string

            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;

            this.vy = 360.0;
            this.vx = -180.0;

            if(leftScore >= Constants.WINNING_SCORE){
                leftScoreText.text = "0";
                Main.changeState(2);
                
            }

        }
    }

}
