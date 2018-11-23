package philosophers;

import java.util.Random;

public class DeadlockSolution extends Thread {
    static boolean[] forks;
    int philo;

    public DeadlockSolution(boolean[] forks, int philo) {
        this.forks = forks;
        this.philo = philo;
    }

    public synchronized void letFork(int i) {
        forks[i] = true;
        this.notify();
    }

    public synchronized void takeFork(int i) {
        while (!forks[i]) try {
            this.wait();
            forks[i] = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat(int philo){
        System.out.println("Philosoph-" + philo + " is eating...");
    }

    public void finish(int philo){
        System.out.println("Philosoph-" + philo + " is finishing...");
    }

    public void think(int philo){
        System.out.println("Philosoph-" + philo + " is thinking...");
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            takeFork(philo);
            takeFork((philo+1)%5);

            eat(philo);
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(1234)+123);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            finish(philo);

            letFork(philo);
            letFork((philo+1)%5);

            think(philo);
            try {
                Thread.sleep(rand.nextInt(1234)+123);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
