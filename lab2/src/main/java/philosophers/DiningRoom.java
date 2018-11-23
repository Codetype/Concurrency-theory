package philosophers;

import com.sun.org.apache.bcel.internal.generic.FASTORE;
import semaphoresTasks.Semafor;

public class DiningRoom {

    public static void main(String[] args) {
        // algorithm0();
        // algorithm1();
        // algorithm2();
        // algorithm3();
    }

    private static void algorithm0() {
        boolean[] forks = new boolean[5];
        for(int i=0; i < 5; i++) forks[i] = true;

        DeadlockSolution[] philos = new DeadlockSolution[5];
        for(int i=0; i < 5; i++) philos[i] = new DeadlockSolution(forks, i);

        for(int i=0; i < 5; i++) philos[i].start();
        try {for(int i=0; i < 5; i++) philos[i].join();}
        catch (InterruptedException e) {e.printStackTrace();}
    }

    private static void algorithm1() {
        Waiter waiter = new Waiter();
        WaiterSolution[] philos = new WaiterSolution[5];
        for (int i = 0; i < 5; i++) philos[i] = new WaiterSolution(waiter, i);

        for (int i = 0; i < 5; i++) philos[i].start();
        try {
            for (int i = 0; i < 5; i++) philos[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void algorithm2() {
        Semafor[] philoSem = new Semafor[5];
        for(int i=0; i < 5; i++) philoSem[i] = new Semafor();

        FastSolution[] philos= new FastSolution[5];
        for(int i=0; i < 5; i++) philos[i] = new FastSolution(philoSem, i);

        for(int i=0; i < 5; i++) philos[i].start();
        try {for(int i=0; i < 5; i++) philos[i].join();}
        catch (InterruptedException e) {e.printStackTrace();}
    }

    private static void algorithm3() {
        Semafor[] forks = new Semafor[5];
        for(int i=0; i < 5; i++) forks[i] = new Semafor();

        HierarchySolution[] philos = new HierarchySolution[5];
        for(int i=0; i < 5; i++) philos[i] = new HierarchySolution(forks, i);

        for(int i=0; i < 5; i++) philos[i].start();
        try {for(int i=0; i < 5; i++) philos[i].join();}
        catch (InterruptedException e) {e.printStackTrace();}
    }
}
