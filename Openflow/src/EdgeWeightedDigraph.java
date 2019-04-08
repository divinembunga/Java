/**
 * EdgeWeightedDigraph Class
 * 
 * @author Divine Mbunga
 */
import java.util.Stack;
public class EdgeWeightedDigraph {
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in the digraph
    private int E;                      // number of edges in the digraph
    private Bag<DirectedEdge>[] adj;    // adjacency list for vertex v
    private int[] indegree;             // indegree of vertex v
    
    /**
     * Initializes an empty edge-weighted digraph with vertices and 0 edges.
     *
     */
    public EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be positive");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    /**
     * Initializes a random edge-weighted digraph with vertices and  edges.
     *
     */
    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be positive");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    /**  
     * Initializes an edge-weighted digraph from the specified input stream.
     *
     */
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be positive");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    /**
     * Initializes a new edge-weighted digraph 
     * 
     */
    public EdgeWeightedDigraph(EdgeWeightedDigraph EWD) {
        this(EWD.V());
        this.E = EWD.E();
        for (int v = 0; v < EWD.V(); v++)
            this.indegree[v] = EWD.indegree(v);
        for (int v = 0; v < EWD.V(); v++) {
            // reverse it to ensure adjacency list is in same order as original
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for (DirectedEdge e : EWD.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    /**
     * Returns the number of vertices in  digraph.
     *
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in digraph.
     *
     */
    public int E() {
        return E;
    }

    /**
     * 
     * Ensures the vertex is correct
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not > 0 and < " + (V-1));
    }

    /**
     * Adds the directed edge to the edge-weighted digraph.
     *
     */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }


    /**
     * Returns the directed edges incident from the vertex.
     *
     */
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from the vertex 
     * 
     *
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     *
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all edges in this edge-weighted digraph, as an iterable.
     *
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    } 

    /**
     * Returns a string of the edge-weighted digraph.
     *
     */
    public String toString() {
        StringBuilder stringB= new StringBuilder();
        stringB.append(V + " " + E + NEW_LINE);
        for (int v = 0; v < V; v++) {
        	stringB.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
            	stringB.append(e + "  ");
            }
            stringB.append(NEW_LINE);
        }
        return stringB.toString();
    }

    /**
     * Unit tests the class.
     *
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}


