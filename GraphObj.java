package graph;

/* See restrictions in Graph.java. */


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Khang
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _vertexArray = new ArrayDeque<Integer>();
        _edgeArray = new ArrayList<ArrayList<Integer>>();
        _maxVertex = 0;
        removedVertex = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return _vertexArray.size();
    }

    @Override
    public int maxVertex() {
        int maxVertex = 0;
        Iteration<Integer> iter = this.vertices();
        while (iter.hasNext()) {
            int temp = iter.next();
            if (maxVertex < temp) {
                maxVertex = temp;
            }
        }
        return maxVertex;

    }

    @Override
    public int edgeSize() {
        Iteration<int[]> iter = this.edges();
        int count = 0;
        while (iter.hasNext()) {
            count++;
            iter.next();
        }
        return count;

    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        int count = 0;
        if (_edgeArray.size() == 0) {
            return 0;
        }
        ArrayList<Integer> adj = _edgeArray.get(v - 1);
        for (int i : adj) {
            if (i == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        if (!_vertexArray.contains(u)) {
            return false;
        }
        return true;

    }

    /** Returns true iff U and V are my vertices and I have an edge (U, V). */
    @Override
    public boolean contains(int u, int v) {
        if (!contains(u) || !contains(v)) {
            return false;
        }
        if (isDirected()) {
            ArrayList<Integer> adj = _edgeArray.get(u - 1);
            return adj.get(v - 1) == 1 ? true : false;
        } else {
            ArrayList<Integer> adj1 = _edgeArray.get(u - 1);
            ArrayList<Integer> adj2 = _edgeArray.get(v - 1);
            return (adj1.get(v - 1) == 1 && (adj2.get(u - 1) == 1));
        }
    }

    /** Returns a new vertex and adds it to me with no incident edges.
     *  The vertex number is always the smallest integer >= 1 that is not
     *  currently one of my vertex numbers.  */
    @Override
    public int add() {
        int output;
        if (_maxVertex == vertexSize()) {
            _maxVertex++;
            _vertexArray.add(_maxVertex);
            output = _maxVertex;
        } else {
            int minIndex = removedVertex.indexOf(
                    Collections.min(removedVertex));
            output = removedVertex.get(minIndex);
            removedVertex.remove(minIndex);
            _vertexArray.add(output);
        }

        for (ArrayList<Integer> temp : _edgeArray) {
            temp.add(0);
        }
        ArrayList<Integer> adjThisVertex  = new ArrayList<Integer>();
        for (int index = 0; index < _maxVertex; index++) {
            adjThisVertex.add(0);
        }
        _edgeArray.add(adjThisVertex);


        return output;

    }

    /** Add an edge incident on U and V. If I am directed, the edge is
     *  directed (leaves U and enters V).  Requires that U and V are my
     *  vertices.  Has no effect if there is already an edge from U to
     *  V.  Returns a unique positive number identifying the edge,
     *  different from those returned for any other existing edge. */
    @Override
    public int add(int u, int v) {
        if (contains(u, v)) {
            return edgeId(u, v);
        }
        if (contains(u) && contains(v)) {
            ArrayList<Integer> adj = _edgeArray.get(u - 1);
            adj.set(v - 1, 1);
            if (!isDirected()) {
                ArrayList<Integer> adj2 = _edgeArray.get(v - 1);
                adj2.set(u - 1, 1);
            }
        }
        return edgeId(u, v);
    }

    /** Remove V, if present, and all adjacent edges. */
    @Override
    public void remove(int v) {
        if (contains(v)) {
            ArrayList<Integer> adj = _edgeArray.get(v - 1);
            for (int i = 0; i < _maxVertex; i++) {
                adj.set(i, 0);
            }
            for (int i = 0; i < _maxVertex; i++) {
                ArrayList<Integer> adj2 = _edgeArray.get(i);
                adj2.set(v - 1, 0);
            }
            _vertexArray.remove(v);
            removedVertex.add(v);
        }


    }

    /** Remove edge (U, V) from me, if present. */
    @Override
    public void remove(int u, int v) {
        if (!(contains(u, v))) {
            return;
        } else {
            ArrayList<Integer> adj = _edgeArray.get(u - 1);
            adj.set(v - 1, 0);
            if (!isDirected()) {
                ArrayList<Integer> adj2 = _edgeArray.get(v - 1);
                adj2.set(u - 1, 0);
            }
        }
    }

    /** Returns an Iteration over all vertices in numerical order. */
    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_vertexArray);

    }

    /** Returns an iteration over all successors of V.
     *  Empty if V is not my vertex. */
    @Override
    public Iteration<Integer> successors(int v) {
        if (contains(v)) {
            ArrayList<Integer> adj = _edgeArray.get(v - 1);

            ArrayDeque<Integer> successor = new ArrayDeque<Integer>();
            for (int i = 0; i < _maxVertex; i++) {
                if (adj.get(i) == 1) {
                    successor.add(i + 1);
                }
            }
            return Iteration.iteration(successor);
        } else {
            ArrayDeque<Integer> emptyList = new ArrayDeque<>();
            return Iteration.iteration(emptyList);
        }
    }

    /** Returns an iteration over all predecessors of V.
     *  Empty if V is not my vertex. */
    @Override
    public abstract Iteration<Integer> predecessors(int v);

    /** Returns an iteration over all edges in me.  Edges are returned
     *  as two-element arrays (u, v), which are directed if the graph
     *  is.  The values in the array returned by .next() may have changed
     *  on the next call of .next() (that is, .next() is free to use the same
     *  array to return all results). */
    @Override
    public Iteration<int[]> edges() {
        ArrayDeque<int[]> edgeList = new ArrayDeque<>();
        if (isDirected()) {
            for (int i = 0; i < _maxVertex; i++) {
                ArrayList<Integer> adj = _edgeArray.get(i);
                for (int j = 0; j < _maxVertex; j++) {
                    if (adj.get(j) != 0) {
                        int[] arr = new int[2];
                        arr[0] = i + 1;
                        arr[1] = j + 1;
                        edgeList.add(arr);
                    }
                }
            }
        } else {

            for (int i = 0; i < _maxVertex; i++) {
                ArrayList<Integer> adj = _edgeArray.get(i);
                for (int j = i; j < _maxVertex; j++) {
                    if (adj.get(j) != 0) {
                        int[] arr = new int[2];
                        arr[0] = i + 1;
                        arr[1] = j + 1;
                        edgeList.add(arr);
                    }
                }
            }
        }

        return Iteration.iteration(edgeList);
    }

    /* Non-public methods for internal use. */
    /** Throw exception if V is not one of my vertices. */
    @Override
    protected void checkMyVertex(int v) {
        if (!this.contains(v)) {
            throw new IllegalArgumentException();
        }
    }

    /** Returns a unique positive identifier for the edge (U, V), if it
     *  is present, or 0 otherwise.  If edges are not removed from the graph,
     *  this value should be a small multiple of the number of the edges in
     *  the graph. Its purpose is to provide a mapping of edges to integers
     *  for use by classes such as LabeledGraph. It is the same value as that
     *  returned by add(u, v). */
    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected()) {
            if (v > u) {
                return ((v - 1) * (v - 1) + 3 * (v - 1)) / 2
                        - (Math.max(u - 1, v - 1) - Math.min(u - 1, v - 1));
            } else if (v == u) {
                return ((v - 1) * (v - 1) + 3 * (v - 1)) / 2;
            } else {
                return ((u - 1) * (u - 1) + 3 * (u - 1)) / 2
                        - (Math.max(u - 1, v - 1) - Math.min(u - 1, v - 1));
            }
        } else {
            return ((Math.max(u, v) * (Math.max(u, v)))
                    - Math.max(u, v) + 1) + (u - v);
        }
    }

    /**
     * Array contains edge.
     */
    protected ArrayList<ArrayList<Integer>> _edgeArray;
    /**
     * Vertex Array.
     */
    protected ArrayDeque<Integer> _vertexArray;
    /** Max index. */
    protected int _maxVertex;
    /**
     * removed Vertices.
     */
    protected ArrayList<Integer> removedVertex;


}
