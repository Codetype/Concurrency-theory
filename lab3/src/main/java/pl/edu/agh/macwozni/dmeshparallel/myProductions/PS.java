package pl.edu.agh.macwozni.dmeshparallel.myProductions;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.production.AbstractProduction;
import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;

public class PS extends AbstractProduction<Vertex> {
    public Vertex[][] vMatrix;

    public PS(Vertex v, Vertex[][] vMatrix, PDrawer<Vertex> _drawer) {
        super(v, _drawer);
        this.vMatrix = vMatrix;
    }

    @Override
    public Vertex apply(Vertex s) {
        Vertex t1 = new Vertex(s.x, s.y + 1, "PS");
        vMatrix[t1.y][t1.x] = t1;
        // System.out.println("PS");

        return t1;
    }
}
