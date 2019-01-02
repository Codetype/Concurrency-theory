package pl.edu.agh.macwozni.dmeshparallel;

import pl.edu.agh.macwozni.dmeshparallel.mesh.MyDrawer;
import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.mesh.GraphDrawer;
import pl.edu.agh.macwozni.dmeshparallel.myProductions.*;
import pl.edu.agh.macwozni.dmeshparallel.parallelism.BlockRunner;
import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;

import java.lang.Math;

public class Executor extends Thread {
    private final BlockRunner runner;
    public int M;
    public int N;

    public Executor(BlockRunner _runner, int m, int n){
        this.M = m;
        this.N = n;
        this.runner = _runner;
    }

    @Override
    public void run() {
        //initialize drawer
        PDrawer drawer = new GraphDrawer();

        //source vertex
        Vertex s = new Vertex(0, 0, "S");

        //initialize matrix and its first source element
        Vertex[][] vMatrix = new Vertex[this.M][this.N];
        vMatrix[0][0] = s;

        for (int t = 1; t <= 2 * Math.max(this.N, this.M) - 2; t++) {

            for (int i = 0; i <= this.M - 1; i++) {
                int j = t - i - 1;
                if (j >= 0 && j < this.N - 1) {
                    //adding 'east' production
                    Vertex v = vMatrix[i][j];
                    PE pE = new PE(v, vMatrix, drawer);
                    this.runner.addThread(pE);

                }
            }
                if (t <= this.M - 1) {
                    //adding 'south' production
                    Vertex v = vMatrix[t - 1][0];
                    PS pS = new PS(v, vMatrix, drawer);
                    this.runner.addThread(pS);

                }
            this.runner.startAll();
        }

        System.out.println("Done");
        MyDrawer myDrawer = new MyDrawer();
        myDrawer.draw(vMatrix, this.M, this.N);

    }
}