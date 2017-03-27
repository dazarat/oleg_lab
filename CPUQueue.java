package parallel2;

import java.util.ArrayDeque;

public class CPUQueue {
    private ArrayDeque<Process> processes;
    private int size;

    public CPUQueue(int size) {
        this.size = size;
        processes = new ArrayDeque<Process>();
    }

    public synchronized boolean isEmpty() {
        return processes.isEmpty();
    }

    public int getSize() {
        return size;
    }

    public synchronized void push(Process process) {
        if (processes.size() >= size)
            throw new ArrayIndexOutOfBoundsException();

        processes.add(process);
        System.out.println(String.format("pushed to queue %d, size : %d", process.getId(), processes.size()));
    }

    public synchronized Process pop() {
        Process res =  processes.poll();
        if (res != null) {
            //System.out.println(String.format("popped from queue %d", res.getId()));
        }
        return res;
    }
}
