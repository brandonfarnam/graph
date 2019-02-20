package graph;

/**
 * ShortestPath example which extends from SimpleShortestPaths
 * which is used for testing.
 * @author Khang
 */
public class ShortestPathSample extends SimpleShortestPaths {

    /**
     * Constructor.
     * @param G this is parameter for graph
     * @param source this is parameter
     */
    public ShortestPathSample(Graph G, int source) {
        super(G, source);
    }

    /**
     * shortestpath sample.
     * @param G this is parameter for graph
     * @param source this is parameter for source
     * @param dest this is parameter for destination
     */
    public ShortestPathSample(Graph G, int source, int dest) {
        super(G, source, dest);
    }

    /**
     *
     * @param u
     * @param v
     * @return
     */
    @Override
    protected double getWeight(int u, int v) {
        return 0.0;
    }
}

