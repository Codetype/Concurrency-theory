package semaphoresTasks;

public class Main {
    //zad1
    public static void main(String[] args) {
        Counter race = new Counter();
        Race race1 = new Race( true, race);
        Race race2 = new Race(false, race);
        race1.start();
        race2.start();

        try {
            race1.join();
            race2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Race final value: " + race.getCounter());
    }

}
