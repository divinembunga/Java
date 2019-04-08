/**
 * DijkstraSP Class
 * 
 * @author Divine Mbunga
 */
import java.util.Stack;

public class DijkstraSP {
	private double[] distTo;          // distTo[v] = distance  of shortest s->v path
	private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Double> pq;    // priority queue of vertices

	/**
	 * Computes a shortest-paths tree from the source vertex {@code s} to every other
	 * vertex in the edge-weighted digraph {@code G}.
	 */
	public DijkstraSP(EdgeWeightedDigraph EWD, int s) {
		for (DirectedEdge eD : EWD.edges()) {
			if (eD.weight() < 0)
				throw new IllegalArgumentException("edge " + eD + " has a negative weight");
		}

		distTo = new double[EWD.V()];
		edgeTo = new DirectedEdge[EWD.V()];

		validateVertex(s);

		for (int v = 0; v < EWD.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(EWD.V());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge eD : EWD.adj(v))
				relax(eD);
		}

		// check optimality conditions
		assert check(EWD, s);
	}

	// relax edge e and update pq if changed
	private void relax(DirectedEdge eD) {
		int v = eD.from(), w = eD.to();
		if (distTo[w] > distTo[v] + eD.weight()) {
			distTo[w] = distTo[v] + eD.weight();
			edgeTo[w] = eD;
			if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else                pq.insert(w, distTo[w]);
		}
	}

	/**
	 * 
	 * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
	 *         {@code Double.POSITIVE_INFINITY} if no such path
	 */
	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	/**
	 *
	 * @return {@code true} if there is a path from the source vertex
	 *         {@code s} to vertex {@code v}; {@code false} otherwise
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/**
	 * 
	 * @return a shortest path from the source vertex {@code s} to vertex {@code v}
	 *         as an iterable of edges, and {@code null} if no such path
	 */
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge eD = edgeTo[v]; eD != null; eD = edgeTo[eD.from()]) {
			path.push(eD);
		}
		return path;
	}


	/* 
	 * check optimality conditions:
	 * (i) for all edges e: distance to if less than or equal to the distance from edge
	 * (ii) for all edge e on the SPT: distance to is equal to the distance from edge
	 */
	private boolean check(EdgeWeightedDigraph EWD, int s) {

		// check that edge weights are nonnegative
		for (DirectedEdge eD : EWD.edges()) {
			if (eD.weight() < 0) {
				System.err.println("negative edge weight detected");
				return false;
			}
		}

		// check that distTo[v] and edgeTo[v] are consistent
		if (distTo[s] != 0.0 || edgeTo[s] != null) {
			System.err.println("distTo[s] and edgeTo[s] are inconsistent");
			return false;
		}
		for (int v = 0; v < EWD.V(); v++) {
			if (v == s) continue;
			if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
				System.err.println("distTo[] and edgeTo[] are inconsistent");
				return false;
			}
		}

		// check that all edges e = v->w satisfies
		for (int v = 0; v < EWD.V(); v++) {
			for (DirectedEdge eD : EWD.adj(v)) {
				int w = eD.to();
				if (distTo[v] + eD.weight() < distTo[w]) {
					System.err.println("edge " + eD + " not relaxed");
					return false;
				}
			}
		}

		// check that all edges e = v->w satisfies
		for (int w = 0; w < EWD.V(); w++) {
			if (edgeTo[w] == null) continue;
			DirectedEdge eD = edgeTo[w];
			int v = eD.from();
			if (w != eD.to()) return false;
			if (distTo[v] + eD.weight() != distTo[w]) {
				System.err.println("edge " + eD + " on shortest path is not tight");
				return false;
			}
		}
		return true;
	}

	
	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not > 0 and <  " + (V-1));
	}


	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedDigraph EWD = new EdgeWeightedDigraph(in);
		int s = Integer.parseInt(args[1]);

		// compute shortest paths
		DijkstraSP dsp = new DijkstraSP(EWD, s);


		// print shortest path
		for (int i = 0; i < EWD.V(); i++) {
			if (dsp.hasPathTo(i)) {
				StdOut.printf("%d to %d (%.2f)  ", s, i, dsp.distTo(i));
				for (DirectedEdge eD : dsp.pathTo(i)) {
					StdOut.print(eD + "   ");
				}
				StdOut.println();
			}
			else {
				StdOut.printf("%d to %d  no path\n", s, i);
			}
		}
	}
}
