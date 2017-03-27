package parallel2;


public class Main {

    public static void main(String[] args) {

        CPUQueue q1 = new CPUQueue(10);

        CPU cpu = new CPU("cpu1", q1);
        CPU cpu2 = new CPU("cpu2", q1);

        ProducerThread pThread1 = new ProducerThread(cpu, cpu2, q1, 2, 1000, 30);

        cpu.start();
        pThread1.start();
        cpu2.start();
        cpu2.setBusy(true);
        System.out.println((double)cpu.getCount() / pThread1.getMaxCount());

    }
}