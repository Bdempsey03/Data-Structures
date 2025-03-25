public class Stopwatch {
    
    private long startTime;
    private long endTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }
    public void stop(){
        endTime = System.currentTimeMillis();
    }
    public long elapsedTime(){//returns seconds
        return (endTime - startTime);
    }
    public void reset(){
        startTime = 0;
        endTime = 0;
    }


}
