package d.games;

public class Main{
    public static int state = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window window;

    public static void main(String[] args){
        //Window window = new Window(); - creates a separate window; want an overlap
        menu = new MainMenu();
        
        mainThread = new Thread(menu);
        mainThread.start();

    }   

    public static void changeState(int newState){

        if(newState == 1 && state == 0){

            menu.stop();

            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();

            //create a screen that pops up saying "You won/lost", that either opens the main menu again or just exits.

        }

        else if(newState == 0 && state == 1){

            window.stop();

            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
            
        }

        else if(newState == 2){
            if(window != null)
                window.stop();
            if(menu != null)
                menu.stop();
        }
        
        state = newState;
        return;
    }
}