package d.games;

public class Time {
    //calling nanotimes

    public static double timeStarted = System.nanoTime();
    
    public static double getTime(){

        return (System.nanoTime() - timeStarted) * 1E-9;
        //returns as int without 1E-9 --> converts to seconds
    }
}
