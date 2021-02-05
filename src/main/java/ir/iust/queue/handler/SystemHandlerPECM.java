package ir.iust.queue.handler;

import ir.iust.queue.model.system.queue1.System1;
import ir.iust.queue.model.system.Task;
import ir.iust.queue.service.InputParametersGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemHandlerPECM {
    @Autowired
    System1 system1;

    @Autowired
    InputParametersGenerator inputParametersGenerator;


    private double clock;
    //    private Map<String, double> eventList = new HashMap<String, double>();
    private double A;
    private double D;
    List<Task> tasksList = new ArrayList<>();

    private int tasksCounter = 0;
    private Task runningTask;
    private int numberServiced = 0;
    private double totalDelay = 0;


    public void initializeSystemParameters() {

//        Get input parameters
        List<Double> fisrtQueueEnterArrivalsList = inputParametersGenerator.fisrtQueueEnterArrivalsTime();
        List<Double> fisrtQueueServiceTime = inputParametersGenerator.fisrtQueueServiceTime();
        for (int i = 0; i < fisrtQueueEnterArrivalsList.size(); i++) {
            Task task = new Task();
            task.setEnterArrivalTime(fisrtQueueEnterArrivalsList.get(i));
            task.setServiceTime(fisrtQueueServiceTime.get(i));
            tasksList.add(task);
        }

//        initialize system state
        system1.getSystemState().setServerStatus(0);
        system1.getSystemState().setNumberInQueue(0);
        system1.getSystemState().setTimeOfLastEvent(0);

//        initialize statical counters
        system1.getStaticalCounters().setNumberServiced(0);
        system1.getStaticalCounters().setTotalDelay(0);
        system1.getStaticalCounters().setAreaUnderQt(0);
        system1.getStaticalCounters().setGetAreaUnderBt(0);

        clock = 0;
        A = tasksList.get(0).getEnterArrivalTime();
        D =  Double.POSITIVE_INFINITY;
        print();
    }

    Double Qt = 0.0;
    Double timeOfLastEvent = 0.0;
    List<Double> Es = new ArrayList<>();
    public void run() {
        Double initialA = A;
        Double prevClock = 0.0;

        clock = A;
        D = tasksList.get(0).getServiceTime() + clock;

        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getEnterArrivalTime();
        print();
        numberServiced ++;


        while (true) {

            prevClock = clock;

            if (A < D) {
                clock = A;
                if(D == Double.POSITIVE_INFINITY){
                    initialA += (clock - prevClock);
                }
                java.lang.System.out.println("arrivalEventRaised");
                Qt = Qt + system1.getTasksQueue().size() * (clock - prevClock);
                arrivalEventRaised();
                print();
            } else {

                java.lang.System.out.println("exitEventRaised");
                clock = D;
                if(D == Double.POSITIVE_INFINITY){
                    initialA += (clock - prevClock);
                }
                Qt = Qt + system1.getTasksQueue().size() * (clock - prevClock);
                exitEventRaised();
                print();
                if( runningTask.getServiceTime() == 0.6){
                    for(int i=0;i<tasksList.size();i++){
                        Es.add(tasksList.get(i).getServiceTime());
                        if(tasksList.get(i).getServiceTime() == 0.6)
                            break;
                    }
                    timeOfLastEvent = clock;
                    break;
                }
            }
        }
        Double Wq = (system1.getStaticalCounters().getTotalDelay()) / system1.getStaticalCounters().getNumberServiced();
        Double Bt = clock - initialA;
        Double Lq = (Qt / timeOfLastEvent);
        Double p = (Bt / timeOfLastEvent);
        Double L = Lq + p;
        Double tmp = 0.0;
        for(int i=0;i<Es.size();i++){
//            java.lang.System.out.println(Es.get(i));
            tmp += Es.get(i);
        }
        Double W = Wq + (tmp/numberServiced);


        java.lang.System.out.println("total delay:    " + system1.getStaticalCounters().getTotalDelay());
        java.lang.System.out.println("number serviced:    " + system1.getStaticalCounters().getNumberServiced());
        java.lang.System.out.println("Q(t):    " + Qt);
        java.lang.System.out.println("B(t):    " + Bt + "\n\n");

        java.lang.System.out.println("W(q):    " + Wq);
        java.lang.System.out.println("L(q):    " + Lq);
        java.lang.System.out.println("p:    " + p);
        java.lang.System.out.println("L:    " + L);
        java.lang.System.out.println("E[s]:    " + tmp/numberServiced);
        java.lang.System.out.println("W:    " + W);



    }

    public void arrivalEventRaised() {
        if(D == Double.POSITIVE_INFINITY){
            D = clock + tasksList.get(tasksCounter).getServiceTime();
        }
        else {
            tasksList.get(tasksCounter).setTimesOfArrival(clock);
            system1.setTasksQueue(tasksList.get(tasksCounter));
            java.lang.System.out.println("queue shode: " + tasksList.get(tasksCounter).getEnterArrivalTime());
        }
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getEnterArrivalTime();

    }

    public void exitEventRaised() {
        numberServiced++;
        if(system1.getTasksQueue().size() == 0){
            D =  Double.POSITIVE_INFINITY;
            return;
        }
        runningTask = system1.getTasksQueue().poll();
        java.lang.System.out.println("Az queue umade:   " + runningTask.getServiceTime());
        D = clock + runningTask.getServiceTime();
        system1.getStaticalCounters().setNumberServiced(numberServiced);
        totalDelay = totalDelay + (clock - runningTask.getTimesOfArrival());
        system1.getStaticalCounters().setTotalDelay(totalDelay);

    }

    int counter = 1;

    public void print() {
        java.lang.System.out.println("level: " + counter);
        java.lang.System.out.println("clock: " + clock);
        java.lang.System.out.println("A: " + A);
        java.lang.System.out.println("D: " + D);
        java.lang.System.out.println("TotalDelay: " + system1.getStaticalCounters().getTotalDelay());
        java.lang.System.out.println("numberServiced: " + numberServiced);
        java.lang.System.out.println("Q(t): " + Qt);
        java.lang.System.out.println();
        java.lang.System.out.println();
        counter++;
    }

}
