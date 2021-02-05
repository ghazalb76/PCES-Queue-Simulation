package ir.iust.queue.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DistributionsGenerator {
    Random random = new Random();

    public double generateExponential(double lambda) {
        if (lambda == 0.0)
            return 0.0;
        double randNumber;
        randNumber = random.nextDouble();
        return -1 / (lambda * Math.log(randNumber));
    }

    public double generateUniform(double a, double b) {
        return (a + (b - a) * random.nextDouble());
    }


}
