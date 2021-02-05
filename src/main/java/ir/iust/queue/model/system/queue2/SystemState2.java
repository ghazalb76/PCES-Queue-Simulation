package ir.iust.queue.model.system.queue2;

import ir.iust.queue.model.system.Task;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class SystemState2 {
    private int serverStatus;
    private int numberInQueue;
    private Queue<Task> taksQueue = new LinkedList<>();
    private double timeOfLastEvent;

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    public double getTimeOfLastEvent() {
        return timeOfLastEvent;
    }

    public void setTimeOfLastEvent(double timeOfLastEvent) {
        this.timeOfLastEvent = timeOfLastEvent;
    }

    public Queue<Task> getTaksQueue() {
        return taksQueue;
    }

    public void setTaksQueue(Queue<Task> taksQueue) {
        this.taksQueue = taksQueue;
    }
}
