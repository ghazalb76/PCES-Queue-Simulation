package ir.iust.queue.model.system;

import org.springframework.stereotype.Component;

@Component
public class PerformanceMeasuresQueue1 {

    private double totalDelay;
    private double numberServiced;
    private double Qt;
    private double Bt;

    private double Wq;
    private double Lq;
    private double p;
    private double L;
    private double Es;
    private double W;

    public double getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(double totalDelay) {
        this.totalDelay = totalDelay;
    }

    public double getNumberServiced() {
        return numberServiced;
    }

    public void setNumberServiced(double numberServiced) {
        this.numberServiced = numberServiced;
    }

    public double getQt() {
        return Qt;
    }

    public void setQt(double qt) {
        Qt = qt;
    }

    public double getBt() {
        return Bt;
    }

    public void setBt(double bt) {
        Bt = bt;
    }

    public double getWq() {
        return Wq;
    }

    public void setWq(double wq) {
        Wq = wq;
    }

    public double getLq() {
        return Lq;
    }

    public void setLq(double lq) {
        Lq = lq;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getEs() {
        return Es;
    }

    public void setEs(double es) {
        Es = es;
    }

    public double getW() {
        return W;
    }

    public void setW(double w) {
        W = w;
    }
}
