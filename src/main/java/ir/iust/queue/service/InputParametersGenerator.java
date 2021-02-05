package ir.iust.queue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InputParametersGenerator {
    @Autowired
    DistributionsGenerator distributionsGenerator;

    public List<Double> fisrtQueueEnterArrivalsTime() {
        List<Double> fisrtQueueEnterArrivalsList = new ArrayList<>();
        fisrtQueueEnterArrivalsList.add((double) 0.4);
        fisrtQueueEnterArrivalsList.add((double) 1.2);
        fisrtQueueEnterArrivalsList.add((double) 0.5);
        fisrtQueueEnterArrivalsList.add((double) 1.7);
        fisrtQueueEnterArrivalsList.add((double) 0.2);
        fisrtQueueEnterArrivalsList.add((double) 1.6);
        fisrtQueueEnterArrivalsList.add((double) 0.2);
        fisrtQueueEnterArrivalsList.add((double) 1.4);
        fisrtQueueEnterArrivalsList.add((double) 1.9);
        return fisrtQueueEnterArrivalsList;
    }

    public List<Double> fisrtQueueServiceTime() {
        List<Double> fisrtQueueServiceList = new ArrayList<>();
        fisrtQueueServiceList.add((double) 2.0);
        fisrtQueueServiceList.add((double) 0.7);
        fisrtQueueServiceList.add((double) 0.2);
        fisrtQueueServiceList.add((double) 1.1);
        fisrtQueueServiceList.add((double) 3.7);
        fisrtQueueServiceList.add((double) 0.6);
        fisrtQueueServiceList.add((double) 0.6);
        fisrtQueueServiceList.add((double) 0.6);
        fisrtQueueServiceList.add((double) 0.6);
        return fisrtQueueServiceList;
    }
}
