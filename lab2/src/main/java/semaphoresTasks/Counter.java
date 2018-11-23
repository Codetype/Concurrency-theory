package semaphoresTasks;

class Counter {
    private int counter = 0;
    private Semafor semafor = new Semafor();

    public int getCounter() {
        return this.counter;
    }

    void incrementCounter() {
        semafor.P();
        counter++;
        // System.out.println("Incrementing... Current value: " + getCounter());
        semafor.V();
    }

    void decrementCounter() {
        semafor.P();
        counter--;
        // System.out.println("Decrementing... Current value: " + getCounter());
        semafor.V();
    }

}