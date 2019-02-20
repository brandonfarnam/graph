package graph;

import org.junit.Test;

public class ShortestPathTest {

    @Test
    public void testClientTraversal() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();

        g.add(1, 2);
        g.add(2, 3);
        g.add(3, 4);
        g.add(1, 3);
    }
}
