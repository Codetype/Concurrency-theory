package homework.Productions;

public abstract class Production extends Thread{
    protected final String ID;

    protected Production(String ID){
        this.ID = ID;
    }

    public String getID(){
        return this.ID;
    }

}

