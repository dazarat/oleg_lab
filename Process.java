package parallel2;


public class Process {
    private int id;
    private int timeToCompute;

    public Process(int id, int timeToCompute) {
        this.id = id;
        this.timeToCompute = timeToCompute;
    }

    public int getId() {
        return id;
    }

    public int getTimeToCompute() {
        return timeToCompute;
    }
}
