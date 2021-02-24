import edu.princeton.cs.algs4.*;

public class aBFSPTester {
    public static void main(String[] args) {
        EdgeWeightedDigraph G;
        // load edge weight digraph
        G = new EdgeWeightedDigraph(new In(args[0]));

        // set start point
        int s = Integer.parseInt(args[1]);

        // solve the shortest path problem using DijkstraSP
        BellmanFordSP sp = new BellmanFordSP(G, s);
        for (int t = 0; t < G.V(); t++) // from start point to each point of graph
        {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));  // print total weight from start to end
            if (sp.hasPathTo(t))    // if there is path between s and t
                for (DirectedEdge e : sp.pathTo(t)) // iterate the path along from s to t
                    StdOut.print(e + " ");  // print edge/weight
            StdOut.println();
        }
    }
}
