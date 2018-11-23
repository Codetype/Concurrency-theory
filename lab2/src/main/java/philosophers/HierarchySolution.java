package philosophers;

import semaphoresTasks.Semafor;

import java.util.Random;

public class HierarchySolution extends Thread {
    private final Semafor[] forks;
    private int philo;


    public HierarchySolution(Semafor[] forks, int philo) {
        this.forks = forks;
        this.philo = philo;
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
                Random rand = new Random();

                forks[this.philo == 4 ? (this.philo + 1) % 5 : this.philo].P();
                forks[this.philo == 4 ? this.philo : (this.philo + 1) % 5].P();
                eat(philo);
                try {
                    Thread.sleep(rand.nextInt(1234) + 123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                forks[(this.philo + 1) % 5].V();
                forks[this.philo].V();
                finish(philo);


                think(philo);
                try {
                    Thread.sleep(rand.nextInt(1234) + 123);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
}
