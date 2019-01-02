package pl.edu.agh.macwozni.dmeshparallel.mesh;

public class MyDrawer {
    public void draw(Vertex[][] v, int M, int N) {
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                System.out.print(v[i][j].toString());
            } System.out.println();
        }
    }
}
