package ir.iust.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InputParametersGenerator_PCES {
    @Autowired
    DistributionsGenerator distributionsGenerator;

    private int numberOfServices = 10000;

    List<Double> fisrtQueueEnterArrivalsList = new ArrayList<>();
    List<Double> fisrtQueueServiceList = new ArrayList<>();
    List<Double> secondQueueServiceList = new ArrayList<>();

    public List<Double> fisrtQueueEnterArrivalsTime() {
        for(int i=0;i<numberOfServices;i++){
            fisrtQueueEnterArrivalsList.add(distributionsGenerator.generateExponential(1));
        }
        return fisrtQueueEnterArrivalsList;
    }

    public List<Double> fisrtQueueServiceTime() {
        for(int i=0;i<numberOfServices;i++){
            fisrtQueueServiceList.add(distributionsGenerator.generateExponential(2));
        }
        return fisrtQueueServiceList;
    }

    public List<Double> generateProbability(){
        Random random = new Random();
        List<Double> probabilities = new ArrayList<>();
        for(int i=0; i<numberOfServices; i++){
            probabilities.add(random.nextDouble());
        }
        for(int i=0; i<numberOfServices; i++){
            System.out.println(probabilities.get(i));
        }
        return probabilities;
    }

    public List<Double> secondQueueServiceTime() {
        for(int i=0; i<numberOfServices; i++){
            secondQueueServiceList.add(distributionsGenerator.generateUniform(0.0, 0.5));
        }
        return secondQueueServiceList;
    }
}
