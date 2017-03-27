package parallel2;


public class CPUExecutionThread extends Thread {
    private Process process;
    private CPU cpu;

    public CPUExecutionThread(CPU cpu, Process p) {
        this.cpu = cpu;
        this.process = p;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(process.getTimeToCompute());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s finished computing %d", cpu.getCPUName(), process.getId()));
        cpu.isBusy = false;
    }
}