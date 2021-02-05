package ir.iust.queue.controller;

import ir.iust.queue.handler.PCES_Queue1;
import ir.iust.queue.handler.PCES_Queue2;
import ir.iust.queue.handler.SystemHandler;
import ir.iust.queue.model.system.PerformanceMeasuresQueue1;
import ir.iust.queue.model.system.PerformanceMeasuresQueue2;
import ir.iust.queue.model.system.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@RestController
public class Controller {

    @Autowired
    SystemHandler systemHandler;


    @Autowired
    PCES_Queue1 pces_queue1;

    @Autowired
    PCES_Queue2 pces_queue2;

    List<Task> tasks1 = new ArrayList<>();
    List<Task> tasks2 = new ArrayList<>();

    @GetMapping
    public void test() {
        //Slide examples
//        systemHandler.initializeSystemParameters();
//        systemHandler.run();


        pces_queue1.initializeSystemParameters();
        tasks1 = pces_queue1.run();

        // p = 0.1
        pces_queue2.initializeSystemParameters(tasks1, 0.1);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.1);

//         p = 0.2
        System.out.println("---------------------------------------- probabilities 2 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.2);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.2);

        // p = 0.3
        System.out.println("---------------------------------------- probabilities 3 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.3);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.3);

        // p = 0.4
        System.out.println("---------------------------------------- probabilities 4 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.4);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.4);
//
        // p = 0.5
        System.out.println("---------------------------------------- probabilities 5 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.5);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.5);

        // p = 0.6
        System.out.println("---------------------------------------- probabilities 6 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.6);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.6);

        // p = 0.7
        System.out.println("---------------------------------------- probabilities 7 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.7);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.7);

        // p = 0.8
        System.out.println("---------------------------------------- probabilities 8 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.8);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.8);

        // p = 0.9
        System.out.println("---------------------------------------- probabilities 9 -------------------------------------------------------------");
        pces_queue2.initializeSystemParameters(tasks1, 0.9);
        tasks2 = pces_queue2.run();
        measureResponseTime(0.9);
    }
    public void measureResponseTime(double probability){
        double time = 0.0;
        for(int i=0;i<tasks1.size();i++){
            if(tasks1.get(i).getProbability() < probability)
                time += (tasks1.get(i).getFirstQueueExitTime() - tasks1.get(i).getTimesOfArrival());
            else
                time += (tasks2.get(i).getSecondQueueExitTime() - tasks1.get(i).getTimesOfArrival());
        }

        System.out.println("measureResponseTime:");
        System.out.println((time)/tasks1.size());

    }
}
