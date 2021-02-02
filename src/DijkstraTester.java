import edu.princeton.cs.algs4.*;

public class DijkstraTester {
    public static void main(String[] args) {
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DijkstraSP sp = new DijkstraSP(G, s);
        for (int t = 0; t < G.V(); t++)
        {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t))
                for (DirectedEdge e : sp.pathTo(t))
                    StdOut.print(e + " ");
            StdOut.println();
        }
    }
}
