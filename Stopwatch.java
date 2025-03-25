public class Stopwatch {
    
    private double startTime;
    private double endTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }
    public void stop(){
        endTime = System.currentTimeMillis();
    }
    public double elapsedTime(){//returns seconds
        return (endTime - startTime);
    }
    public void reset(){
        startTime = 0.0;
        endTime = 0.0;
    }


}
