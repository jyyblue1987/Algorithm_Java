import edu.princeton.cs.algs4.*;

public class bBFSPTester {
    public static void main(String[] args) {
        // load weight digraph from file
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(new In(args[0]));

        // start point
        int st = Integer.parseInt(args[1]);

        // calculate using BellmanFord Algorithm
        BellmanFordSP sp = new BellmanFordSP(graph, st);
        for (int n = 0; n < graph.V(); n++) // iterate from start point
        {
            StdOut.print(st + " to " + n);
            StdOut.printf(" (%4.2f): ", sp.distTo(n));  // print total weight from start to n
            // check if there is path between s and t
            if (sp.hasPathTo(n))
                for (DirectedEdge e : sp.pathTo(n)) // iterate the path along from s to n
                    StdOut.print(e + " ");
            StdOut.println();
        }
    }
}
