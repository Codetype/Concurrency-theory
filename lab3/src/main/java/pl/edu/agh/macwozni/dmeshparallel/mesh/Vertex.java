package pl.edu.agh.macwozni.dmeshparallel.mesh;

public class Vertex {
    public int x;
    public int y;
    public String prod;

    public Vertex(int x, int y, String prod) {
        this.x = x;
        this.y = y;
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "[" + this.x + "][" + this.y + "]: " + this.prod + "\t";
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}