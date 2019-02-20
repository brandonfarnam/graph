package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.Queue;

import static java.util.Collections.asLifoQueue;

/** Implements a depth-first traversal of a graph.  Generally, the
 *  client will extend this class, overriding the visit and
 *  postVisit methods, as desired (by default, they do nothing).
 *  @author Khang
 */
public class DepthFirstTraversal extends Traversal {

    /** A depth-first Traversal of G. */
    protected DepthFirstTraversal(Graph G) {
        super(G, queue);
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }

    @Override
    protected boolean shouldPostVisit(int v) {
        return true;
    }

    /**
     * Queue for DFS.
     */
    private static Queue<Integer> queue =
            asLifoQueue(new ArrayDeque<Integer>());
}
