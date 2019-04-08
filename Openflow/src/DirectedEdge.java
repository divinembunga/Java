/**
 * DirectedEdge Class
 * 
 * @author Divine Mbunga
 *
 */
public class DirectedEdge { 
    private final int vertexTail;
    private final int vertexHead;
    private final double weight;

    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
     * the given {@code weight}.
     * @param v the tail vertex
     * @param w the head vertex
     * @param weight the weight of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public DirectedEdge(int vertexTail, int vertexHead, double weight) {
        if (vertexTail < 0) throw new IllegalArgumentException("Vertex names must be positive integers");
        if (vertexHead < 0) throw new IllegalArgumentException("Vertex names must be positive integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is n/a");
        this.vertexTail = vertexTail;
        this.vertexHead = vertexHead;
        this.weight = weight;
    }

    /**
     * @return the tail vertex of the directed edge
     */
    public int from() {
        return vertexTail;
    }

    /**
     * @return the head vertex of the directed edge
     */
    public int to() {
        return vertexHead;
    }

    /**
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }

    /**
     * @return a string representation of the directed edge
     */
    public String toString() {
        return vertexTail + "-->" + vertexHead + " " + String.format("%5.2f", weight);
    }

   
    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }
}
