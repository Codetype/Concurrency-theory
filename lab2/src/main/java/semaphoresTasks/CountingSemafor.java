package semaphoresTasks;

public class CountingSemafor {
    private Semafor access = new Semafor();
    private Semafor resource = new Semafor();
    private int count = 1;

    public CountingSemafor(int count){
        this.count = count;
    }

    public void P(){
        resource.P();
        count -= 1;

        if (count < 1){
            resource.V();
            access.P();
        } else {
            resource.V();
        }
    }

    public void V() {
        resource.P();
        count += 1;

        if (count < 2){
            access.V();
        }

        resource.V();
    }
}

