package parallel2;

import java.util.Random;

public class ProducerThread extends Thread {
    private int computationTime;
    private CPU cpu;
    private CPU cpu2;
    private CPUQueue queue;
    private Random random;
    private int intensity;
    private static int id = 0;
    private int maxCount;
    static boolean cpu2busy;

    public ProducerThread(CPU cpu, CPU cpu2 ,CPUQueue queue, int intensity, int computationTime, int maxCount) {
        this.cpu = cpu;
        this.cpu2 = cpu2;
        this.queue = queue;
        this.random = new Random();
        this.intensity = intensity;
        this.computationTime = computationTime;
        this.maxCount = maxCount;
        cpu2busy = false;
    }

    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public void run() {
        while (id < maxCount) {
            try {
                Thread.sleep(this.random.nextInt(1000 / intensity));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Process p = new Process(id++, random.nextInt(computationTime));
            if (!cpu.execute(p)) {
                try {
                    queue.push(p);
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (!cpu2busy) {
                        System.out.println("cpu2 caught process");
                        cpu2.executeNow(p);
                        cpu2.setBusy(true);
                    }

                }
            }
        }
    }
}
