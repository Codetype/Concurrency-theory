package philosophers;

class Waiter {
    private boolean[] forks = new boolean[5];

    public Waiter() {
        for (int i = 0; i < 5; i++) forks[i] = true;
    }

    public synchronized void takeForks(int i) {
        //take his forks
        forks[i] = forks[(i + 1) % 5] = true;
        this.notify();
    }

    public synchronized void giveForks(int i) {
        // give him two forks
        while (!forks[i] || !forks[(i + 1) % 5]) try {
            this.wait();
            forks[i] = forks[(i + 1) % 5] = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}