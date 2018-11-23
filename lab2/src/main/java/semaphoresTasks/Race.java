package semaphoresTasks;

public class Race extends Thread {
    private Counter counter;
    private boolean isIncreasing;

    Race(boolean isIncreasing, Counter counter) {
        this.isIncreasing = isIncreasing;
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            if(this.isIncreasing) {
                counter.incrementCounter();
            } else {
                counter.decrementCounter();
            }
        }
    }

}
