package ir.iust.queue.handler;

import ir.iust.queue.model.system.Task;
import ir.iust.queue.model.system.queue2.System2;
import ir.iust.queue.service.InputParametersGenerator_PCES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PCES_Queue2 {

    @Autowired
    System2 system2;

    @Autowired
    InputParametersGenerator_PCES inputParametersGenerator;

    private double clock;
    //    private Map<String, double> eventList = new HashMap<String, double>();
    private double A;
    private double D;
    List<Task> tasksList = new ArrayList<>();

    private int tasksCounter = 0;
    private Task runningTask;
    private int numberServiced = 0;
    private double totalDelay = 0;

    Double Qt = 0.0;
    Double timeOfLastEvent = 0.0;
    List<Double> Es = new ArrayList<>();
    int arrivalEventRaisedCounter = 1;


    public void initializeSystemParameters(List<Task> tasks, double probability) {
        tasksList = new ArrayList<>();
        tasksCounter = 0;
        numberServiced = 0;
        totalDelay = 0;
        Qt = 0.0;
        timeOfLastEvent = 0.0;
        Es = new ArrayList<>();
        arrivalEventRaisedCounter = 1;

//        Get input parameters
        List<Double> secondQueueServiceTime = inputParametersGenerator.secondQueueServiceTime();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = new Task();
            if(tasks.get(i).getProbability() >= probability) {
                task.setSecondQueueEnterArrivalTime(tasks.get(i).getFirstQueueExitTime());
            }
            task.setSecondQueueServiceTime(secondQueueServiceTime.get(i));
            tasksList.add(task);
        }

//        initialize system state
        system2.getSystemState().setServerStatus(0);
        system2.getSystemState().setNumberInQueue(0);
        system2.getSystemState().setTimeOfLastEvent(0);

//        initialize statical counters
        system2.getStaticalCounters().setNumberServiced(0);
        system2.getStaticalCounters().setTotalDelay(0);
        system2.getStaticalCounters().setAreaUnderQt(0);
        system2.getStaticalCounters().setGetAreaUnderBt(0);

        clock = 0;
        A = tasksList.get(0).getSecondQueueEnterArrivalTime();
        D =  Double.POSITIVE_INFINITY;
        print();
    }

    public List<Task> run() {
        Double initialA = A;
        Double prevClock = 0.0;

        clock = A;
        D = tasksList.get(0).getSecondQueueServiceTime() + clock;

        java.lang.System.out.println("arrivalEventRaised");
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getSecondQueueEnterArrivalTime();
//        print();

        int exitEventRaisedCounter = 0;
        while (true){
            if(arrivalEventRaisedCounter == tasksList.size()) {
                break;
            }

            prevClock = clock;

            if (A < D) {
                clock = A;
                if(D == Double.POSITIVE_INFINITY){
                    initialA += (clock - prevClock);
                }
                java.lang.System.out.println("arrivalEventRaised");
                arrivalEventRaisedCounter ++;
                Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
                arrivalEventRaised();
                print();
            } else {

                java.lang.System.out.println("exitEventRaised");
                exitEventRaisedCounter ++;
                clock = D;
                if(D == Double.POSITIVE_INFINITY){
                    initialA += (clock - prevClock);
                }
                Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
                exitEventRaised();
                print();
            }

        }

        timeOfLastEvent = clock;

        for(int i=0;i<tasksList.size();i++){
            Es.add(tasksList.get(i).getSecondQueueServiceTime());
        }

        for(int i=0;i<tasksList.size();i++){
            Es.add(tasksList.get(i).getSecondQueueServiceTime());
        }
        timeOfLastEvent = clock;

        while (true){
            if(exitEventRaisedCounter == tasksList.size())
                break;
//            java.lang.System.out.println("exitEventRaised");
            exitEventRaisedCounter ++;
            clock = D;
            if(D == Double.POSITIVE_INFINITY){
                initialA += (clock - prevClock);
            }
            Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
            exitEventRaised();
            print();

        }

        Double Wq = (system2.getStaticalCounters().getTotalDelay()) / system2.getStaticalCounters().getNumberServiced();
        Double Bt = clock - initialA;
        Double Lq = (Qt / timeOfLastEvent);
        Double p = (Bt / timeOfLastEvent);
        Double L = Lq + p;
        Double tmp = 0.0;
        for(int i=0;i<Es.size();i++){
            tmp += Es.get(i);
        }
        Double W = Wq + (tmp/numberServiced);


        java.lang.System.out.println("total delay:    " + system2.getStaticalCounters().getTotalDelay());
        java.lang.System.out.println("number serviced:    " + system2.getStaticalCounters().getNumberServiced());
        java.lang.System.out.println("Q(t):    " + Qt);
        java.lang.System.out.println("B(t):    " + Bt + "\n\n");

        java.lang.System.out.println("W(q):    " + Wq);
        java.lang.System.out.println("L(q):    " + Lq);
        java.lang.System.out.println("p:    " + p);
        java.lang.System.out.println("L:    " + L);
        java.lang.System.out.println("E[s]:    " + tmp/numberServiced);
        java.lang.System.out.println("W:    " + W);

        return tasksList;

    }

    public void arrivalEventRaised() {
        if(D == Double.POSITIVE_INFINITY){
            D = clock + tasksList.get(tasksCounter).getSecondQueueServiceTime();
        }
        else {
            tasksList.get(tasksCounter).setTimesOfArrival(clock);
            system2.setTasksQueue(tasksList.get(tasksCounter));
//            java.lang.System.out.println("queue shode: " + tasksList.get(tasksCounter).getSecondQueueEnterArrivalTime());
        }
        if(arrivalEventRaisedCounter == tasksList.size()-1)
            return;
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getSecondQueueEnterArrivalTime();

    }

    public void exitEventRaised() {
        numberServiced++;
        tasksList.get(numberServiced-1).setSecondQueueExitTime(clock);
        if(system2.getTasksQueue().size() == 0){
            system2.getStaticalCounters().setNumberServiced(numberServiced);
            D =  Double.POSITIVE_INFINITY;
            return;
        }
        runningTask = system2.getTasksQueue().poll();
//        java.lang.System.out.println("Az queue umade:   " + runningTask.getSecondQueueServiceTime());
        D = clock + runningTask.getSecondQueueServiceTime();
        system2.getStaticalCounters().setNumberServiced(numberServiced);
        totalDelay = totalDelay + (clock - runningTask.getTimesOfArrival());
        system2.getStaticalCounters().setTotalDelay(totalDelay);

    }

    int counter = 1;

    public void print() {
        java.lang.System.out.println("level: " + counter);
        java.lang.System.out.println("clock: " + clock);
        java.lang.System.out.println("A: " + A);
        java.lang.System.out.println("D: " + D);
        java.lang.System.out.println("TotalDelay: " + system2.getStaticalCounters().getTotalDelay());
        java.lang.System.out.println("numberServiced: " + numberServiced);
        java.lang.System.out.println("Q(t): " + Qt);
        java.lang.System.out.println();
        java.lang.System.out.println();
        counter++;
    }

    public void printInitials() {
        for(int i=0;i<tasksList.size();i++){
            java.lang.System.out.println("task"+i+":\t"+tasksList.get(i).getSecondQueueEnterArrivalTime()+"\t"+tasksList.get(i).getSecondQueueServiceTime());
        }
    }
}
