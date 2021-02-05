package ir.iust.queue.model.system.queue2;

import org.springframework.stereotype.Component;

@Component
public class StaticalCounters2 {
    private int numberServiced;
    private double totalDelay;
    private double areaUnderQt;
    private double getAreaUnderBt;

    public int getNumberServiced() {
        return numberServiced;
    }

    public void setNumberServiced(int numberServiced) {
        this.numberServiced = numberServiced;
    }

    public double getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(double totalDelay) {
        this.totalDelay = totalDelay;
    }

    public double getAreaUnderQt() {
        return areaUnderQt;
    }

    public void setAreaUnderQt(double areaUnderQt) {
        this.areaUnderQt = areaUnderQt;
    }

    public double getGetAreaUnderBt() {
        return getAreaUnderBt;
    }

    public void setGetAreaUnderBt(double getAreaUnderBt) {
        this.getAreaUnderBt = getAreaUnderBt;
    }
}
