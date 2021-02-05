package ir.iust.queue.model.system;

import org.springframework.stereotype.Component;

@Component
public class Task {
    private double enterArrivalTime;
    private double serviceTime;
    private double timesOfArrival;

    private double firstQueueExitTime = 0;
    private double secondQueueEnterArrivalTime = 0;
    private double secondQueueServiceTime = 0;
    private double secondQueueExitTime = 0;
    private double probability = -1;

    public double getEnterArrivalTime() {
        return enterArrivalTime;
    }

    public void setEnterArrivalTime(double enterArrivalTime) {
        this.enterArrivalTime = enterArrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getTimesOfArrival() {
        return timesOfArrival;
    }

    public void setTimesOfArrival(double timesOfArrival) {
        this.timesOfArrival = timesOfArrival;
    }

    public double getFirstQueueExitTime() {
        return firstQueueExitTime;
    }

    public void setFirstQueueExitTime(double firstQueueExitTime) {
        this.firstQueueExitTime = firstQueueExitTime;
    }

    public double getSecondQueueExitTime() {
        return secondQueueExitTime;
    }

    public void setSecondQueueExitTime(double secondQueueExitTime) {
        this.secondQueueExitTime = secondQueueExitTime;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getSecondQueueEnterArrivalTime() {
        return secondQueueEnterArrivalTime;
    }

    public void setSecondQueueEnterArrivalTime(double secondQueueEnterArrivalTime) {
        this.secondQueueEnterArrivalTime = secondQueueEnterArrivalTime;
    }

    public double getSecondQueueServiceTime() {
        return secondQueueServiceTime;
    }

    public void setSecondQueueServiceTime(double secondQueueServiceTime) {
        this.secondQueueServiceTime = secondQueueServiceTime;
    }
}
