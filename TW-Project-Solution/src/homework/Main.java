package homework;

import homework.Executor.Executor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Executor ex1 = new Executor();
        } catch (IOException e) {
            System.out.println("IOException occured");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException occured");
        }
    }
}
