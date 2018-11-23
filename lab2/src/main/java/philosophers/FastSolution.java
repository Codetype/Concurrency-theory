package philosophers;

import semaphoresTasks.Semafor;

import java.util.Random;

public class FastSolution extends Thread {
        static Semafor[] philoSem;
        int philo;

        public FastSolution(Semafor[] philoSem, int philo) {
            this.philoSem = philoSem;
            this.philo = philo;
        }

        public synchronized void takeForks() throws InterruptedException {
            philoSem[((this.philo - 1) % 5 + 5) % 5].P();
            philoSem[this.philo].P();
        }

        public synchronized void letForks() {
            philoSem[((this.philo - 1) % 5 + 5) % 5].V();
            philoSem[this.philo].V();
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
                try {
                    takeForks();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eat(philo );
                Random rand = new Random();
                try {
                    Thread.sleep(rand.nextInt(1234)+123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish(philo);
                letForks();
                think(philo);
                try {
                    Thread.sleep(rand.nextInt(1234)+123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
