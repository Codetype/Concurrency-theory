package semaphoresTasks;

public class Semafor {

    private int _czeka = 1;
    private Object _stan = new Object();

    public void P() {
        try{
            synchronized (_stan) {
                while(_czeka == 0) {
                    _stan.wait();
                }
                _czeka = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void V() {
        synchronized (_stan) {
            if (_czeka == 0) {
                _czeka = 1;
                _stan.notify();
            }
        }
    }
}