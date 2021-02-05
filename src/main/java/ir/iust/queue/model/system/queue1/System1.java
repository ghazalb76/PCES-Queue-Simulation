package ir.iust.queue.model.system.queue1;

import ir.iust.queue.model.system.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class System1 {
    private double arrivalTime = 0;
    private Queue<Task> tasksQueue = new LinkedList<Task>();

    @Autowired
    SystemState1 systemState;
    @Autowired
    StaticalCounters1 staticalCounters;

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Queue<Task> getTasksQueue() {
        return tasksQueue;
    }

    public void setTasksQueue(Task task) {
        this.tasksQueue.add(task);
    }

    public SystemState1 getSystemState() {
        return systemState;
    }

    public void setSystemState(SystemState1 systemState) {
        this.systemState = systemState;
    }

    public StaticalCounters1 getStaticalCounters() {
        return staticalCounters;
    }

    public void setStaticalCounters(StaticalCounters1 staticalCounters) {
        this.staticalCounters = staticalCounters;
    }
}
