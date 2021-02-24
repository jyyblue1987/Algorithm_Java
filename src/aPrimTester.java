import edu.princeton.cs.algs4.*;

public class aPrimTester {
    public static void main(String[] args) {
        EdgeWeightedGraph G;
        // load edge weight digraph
        G = new EdgeWeightedGraph(new In(args[0]));

        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }

        StdOut.printf("%.5f\n", mst.weight());

    }
}
