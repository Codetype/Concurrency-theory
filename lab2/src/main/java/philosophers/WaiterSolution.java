package philosophers;

import java.util.Random;

public class WaiterSolution extends Thread {
        private Waiter waiter;
        private int philo;

        public WaiterSolution(Waiter waiter, int philo) {
            this.waiter = waiter;
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
                waiter.giveForks(philo);
                eat(philo);

                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(1234) + 123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish(philo);
                waiter.takeForks(philo);

                think(philo);

                try {
                    Thread.sleep(rand.nextInt(1234) + 123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
