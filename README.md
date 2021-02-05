# PCES-Queue-Simulation
This project is a ‪Performance Evaluation of Computer Systems (PCES) which has been written using Spring Boot and evaluates the performance of the system. 
Assume the queue network shown below: <br />
![Alt text](SystemQueue.png?raw=true "Queue Network") <br />
This network contains two queues. First of all tasks enter the system from first queue. When the task has been serviced in first queue, it will enter to second queue with probability of p and it will exit from the system with probability of 1-p. Enter arrival time of the tasks follows 
Exponential distribution with rate of 1, time which first queue services tasks follows Exponential distribution with rate 2 and time which second queue services tasks follows Uniform distribution with U(0.0, 0.5).
