import edu.princeton.cs.algs4.*;

public class aLazyPrimTester {

    public static void main(String[] args) {
        EdgeWeightedGraph G;
        // load edge weight digraph
        G = new EdgeWeightedGraph(new In(args[0]));

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }

        StdOut.printf("%.5f\n", mst.weight());

    }
}
