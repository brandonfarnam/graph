package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.ArrayList;

/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author Khang
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        ArrayList<Integer> adj = _edgeArray.get(v - 1);
        for (int i : adj) {
            if (i != 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        if (contains(v)) {
            ArrayList<Integer> adj = _edgeArray.get(v - 1);

            ArrayDeque<Integer> predecessors = new ArrayDeque<Integer>();
            for (int i = 0; i < _maxVertex; i++) {
                if (adj.get(i) == 1) {
                    predecessors.add(i + 1);
                }
            }
            return Iteration.iteration(predecessors);
        } else {
            ArrayDeque<Integer> emptyList = new ArrayDeque<>();
            return Iteration.iteration(emptyList);
        }
    }
}
