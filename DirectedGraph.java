package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Khang
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        if (this.contains(v)) {
            for (int i = 0; i < maxVertex(); i++) {
                ArrayList<Integer> adj = _edgeArray.get(i);
                if (adj.get(v - 1) == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        if (contains(v)) {
            ArrayDeque<Integer> successor = new ArrayDeque<Integer>();
            for (int i = 0; i < _maxVertex; i++) {
                ArrayList<Integer> adj = _edgeArray.get(i);
                if (adj.get(v - 1) == 1) {
                    successor.add(i + 1);
                }
            }
            return Iteration.iteration(successor);
        } else {
            ArrayDeque<Integer> emptyList = new ArrayDeque<>();
            return Iteration.iteration(emptyList);
        }
    }
}
