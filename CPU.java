package parallel2;

public class CPU extends Thread {
    private String name;
    private final CPUQueue q1;
    public volatile Boolean isBusy;
    private int count = 0;

    public CPU(String name, CPUQueue q1) {
        this.name = name;
        this.isBusy = false;
        this.q1 = q1;

    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    public String getCPUName() {
        return name;
    }

    public synchronized Boolean getIsBusy() {
        return this.isBusy;
    }

    public int getCount() {
        return count;
    }

    public synchronized boolean execute(Process p) {
        if (this.isBusy) return false;
        System.out.println(String.format("%s started computing  %d", name, p.getId()));
        this.isBusy = true;
        new CPUExecutionThread(this, p).start();
        return true;
    }

    public synchronized boolean executeNow(Process p) {
        ProducerThread.cpu2busy = true;
        System.out.println(String.format("%s started computing  %d", name, p.getId()));
        new CPUExecutionThread(this, p).start();
        this.isBusy = true;
        ProducerThread.cpu2busy = false;
        return true;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (this.isBusy) continue;
                Process p;
                p = q1.pop();
                if (p != null) {
                    this.execute(p);
                    count++;
                }
            }
        }
    }
}
