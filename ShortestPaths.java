package graph;

/* See restrictions in Graph.java. */


import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.AbstractQueue;
import java.util.Queue;


/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Khang
 */
public abstract class ShortestPaths {
    /**
     * A star Traversal.
     */
    class ATraversal extends Traversal {

        /**
         * A Traversal of G, using FRINGE as the fringe.
         *
         * @param G
         * @param fringe
         */
        protected ATraversal(Graph G, Queue<Integer> fringe) {
            super(G, fringe);
        }

        /** Perform a visit on vertex V.  Returns false iff the traversal is to
         *  terminate immediately. */
        @Override
        protected boolean visit(int v) {
            if (v == getDest()) {
                return false;
            } else {
                for (Integer suc : _G.successors(v)) {
                    double weightSuc = getWeight(suc);
                    double vWeight = getWeight(v) + getWeight(v, suc);

                    if (weightSuc > vWeight) {
                        setWeight(suc, vWeight);
                        setPredecessor(suc, v);
                    }
                }
                return true;
            }

        }

    }

    /**
     * A Star Queue extends from abstract queue.
     */
    class AStarQueue extends AbstractQueue<Integer> {
        /**
         * Customize Astar Queue.
         */
        AStarQueue() {
            vNumbers = new ArrayList<Integer>();
            _verticesList = new
                    PriorityQueue<Vertex>(1, new Comparator<Vertex>() {
                        @Override
                public int compare(Vertex o1, Vertex o2) {
                            if (o1.getValue() == o2.getValue()) {
                                return 0;
                            } else if (o1.getValue() > o2.getValue()) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });
        }
        @Override
        public Iterator<Integer> iterator() {
            return vNumbers.iterator();
        }

        @Override
        public int size() {
            return _verticesList.size();
        }

        @Override
        public boolean offer(Integer integer) {
            vNumbers.add(integer);
            Vertex addVertex = new Vertex(integer, getWeight(integer)
                    + estimatedDistance(integer));
            return _verticesList.offer(addVertex);
        }

        @Override
        public Integer poll() {
            Vertex v = _verticesList.poll();
            if (v != null) {

                vNumbers.remove(Integer.valueOf(v.getVertex()));
                return v.getVertex();
            }
            return null;
        }

        @Override
        public Integer peek() {
            Vertex v = _verticesList.peek();
            if (v != null) {
                return v.getVertex();
            }
            return null;
        }

        /**
         * Vertex Object. which contains 2 members which
         * are vertex and the weight of vertex.
         */
        class Vertex {
            /**
             * value of vertex.
             */
            private int vertex;
            /**
             * estimateDistance + weight.
             */
            private double value;

            /**
             * Constructor of Vertex object.
             * @param vertex2 vertex value.
             * @param value2 the weight: estimate distance + weight of vertex.
             */
            Vertex(int vertex2, double value2) {
                this.vertex = vertex2;
                this.value = value2;
            }

            /**
             * get value of vertex.
             * @return int.
             */
            protected int getVertex() {
                return this.vertex;
            }

            /**
             * Get value.
             * @return
             */
            protected double getValue() {
                return this.value;
            }
        }

        /** List store the vertex. */
        private PriorityQueue<Vertex> _verticesList;

        /** ArrayList store the vertex number. */
        private ArrayList<Integer> vNumbers;
    }

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        _traversal = new ATraversal(_G, new AStarQueue());
        for (Integer i : _G.vertices()) {
            setWeight(i, Double.POSITIVE_INFINITY);
            setPredecessor(i, 0);
        }
        setWeight(getSource(), 0);
        _traversal.traverse(getSource());
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        LinkedList<Integer> shortest = new LinkedList<>();
        while (v != getSource()) {
            shortest.addFirst(v);
            v = getPredecessor(v);
        }
        shortest.addFirst(getSource());
        return shortest;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }
    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /**
     * LinkedList<Integer> traverse graph.
     */
    private ATraversal _traversal;

}
