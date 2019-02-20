package graph;

/* See restrictions in Graph.java. */

import java.util.HashMap;

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Khang
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _predecessor = new HashMap<Integer, Integer>();
        _weight = new HashMap<Integer, Double>();
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        if (_weight.containsKey(v)) {
            return _weight.get(v);
        }
        return Integer.MAX_VALUE;

    }

    @Override
    protected void setWeight(int v, double w) {
        _weight.put(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (_predecessor.containsKey(v)) {
            return _predecessor.get(v);
        }
        return 0;
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessor.put(v, u);
    }

    /**
     * HashMap<Integer, Integer> contains the predecessors.
     */
    private HashMap<Integer, Integer> _predecessor;
    /**
     * HashMap<Integer, Double> contains weights of nodes.
     */
    private HashMap<Integer, Double> _weight;
}
