package graph;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/** Unit tests for the Graph class.
 *  @author Khang
 */
public class GraphTest {

    @Test
    public void testAdd() {
        DirectedGraph g = new DirectedGraph();
        UndirectedGraph uG = new UndirectedGraph();

        assertEquals(1, uG.add());
        assertEquals(2, uG.add());
        assertEquals(3, uG.add());
        assertEquals(1, uG.add(1, 2));
        assertEquals(1, uG.add(1, 2));
        assertEquals(4, uG.add(2, 3));
        assertEquals(4, uG.add());
        assertEquals(5, uG.add());

        assertEquals(1, g.add());
        assertEquals(2, g.add());
        assertEquals(3, g.add());
        assertEquals(4, g.add());
        assertEquals(5, g.add());
        assertEquals(2, g.add(1, 2));
        assertEquals(5, g.add(1, 3));
        assertEquals(12, g.add(3, 4));
        assertEquals(6, g.add(2, 3));


    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex3() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.checkMyVertex(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex4() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.checkMyVertex(1);
        uDGraph.checkMyVertex(2);
    }

    @Test
    public void testContains() {
        DirectedGraph g = new DirectedGraph();
        assertEquals(1, g.add());
        assertEquals(true, g.contains(1));
        assertEquals(2, g.add());
        assertEquals(true, g.contains(2));
        assertEquals(3, g.add());
        assertEquals(true, g.contains(3));
        assertEquals(true, g.contains(2));
        assertEquals(2, g.add(1, 2));
        assertEquals(true, g.contains(1, 2));
        assertEquals(false, g.contains(2, 1));
        assertEquals(4, g.add());
        g.add(1, 4);
        assertEquals(false, g.contains(1, 5));
        assertEquals(6, g.add(2, 3));
        assertEquals(true, g.contains(2, 3));
        assertEquals(true, g.contains(1, 4));

        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(1, uG.add());
        uG.add();
        assertEquals(false, uG.contains(3));
        assertEquals(true, uG.contains(2));
        uG.add();
        assertEquals(true, uG.contains(3));
        uG.remove(3);
        assertEquals(false, uG.contains(3));
    }

    @Test
    public void testVertexSize() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.vertexSize());
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(6, dGraph.vertexSize());

        dGraph.remove(2);
        assertEquals(5, dGraph.vertexSize());

        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.vertexSize());
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        assertEquals(4, uDGraph.vertexSize());
        uDGraph.remove(4);
        assertEquals(3, uDGraph.vertexSize());
    }

    @Test
    public void testMaxVertex() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.maxVertex());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        assertEquals(4, dGraph.add());
        assertEquals(5, dGraph.add());

        assertEquals(5, dGraph.maxVertex());
        dGraph.remove(5);
        assertEquals(4, dGraph.maxVertex());

        assertEquals(5, dGraph.add());
        assertEquals(5, dGraph.maxVertex());

        dGraph.remove(3);
        assertEquals(3, dGraph.add());
        dGraph.remove(5);
        System.out.println(dGraph.maxVertex());
        assertEquals(4, dGraph.maxVertex());

        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.maxVertex());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        assertEquals(3, uDGraph.maxVertex());
        assertEquals(4, uDGraph.add());
        assertEquals(4, uDGraph.maxVertex());
    }

    @Test
    public void testEdgeSize() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.edgeSize());
        assertEquals(1, dGraph.add());
        assertEquals(2, dGraph.add());
        assertEquals(3, dGraph.add());
        dGraph.add(1, 2);
        dGraph.add(2, 3);
        assertEquals(2, dGraph.edgeSize());

        UndirectedGraph uDGraph = new UndirectedGraph();

        assertEquals(0, uDGraph.edgeSize());
        assertEquals(1, uDGraph.add());
        assertEquals(2, uDGraph.add());
        assertEquals(3, uDGraph.add());
        uDGraph.add(2, 3);
        uDGraph.add(3, 1);
        uDGraph.add(1, 2);

        assertEquals(3, uDGraph.edgeSize());
    }

    @Test
    public void testIsDirected() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(true, dGraph.isDirected());
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(false, uDGraph.isDirected());
    }

    @Test
    public void testOutDegree() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.outDegree(1));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(0, dGraph.outDegree(2));
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        assertEquals(2, dGraph.outDegree(1));
        assertEquals(0, dGraph.outDegree(3));
        dGraph.add(3, 1);
        assertEquals(1, dGraph.outDegree(3));
    }

    @Test
    public void testInDegree() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(0, dGraph.inDegree(1));
        dGraph.add();
        dGraph.add();
        dGraph.add();
        assertEquals(0, dGraph.inDegree(2));
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        assertEquals(0, dGraph.inDegree(1));
        assertEquals(1, dGraph.inDegree(3));
        dGraph.add(3, 1);
        assertEquals(1, dGraph.inDegree(1));
        dGraph.add(2, 1);
        assertEquals(2, dGraph.inDegree(1));
    }

    @Test
    public void testDegree() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(0, uDGraph.degree(1));
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        assertEquals(0, uDGraph.degree(2));
        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        assertEquals(2, uDGraph.degree(1));
        assertEquals(1, uDGraph.degree(3));
        uDGraph.add(3, 1);
        assertEquals(1, uDGraph.degree(3));
    }

    @Test
    public void testRemove() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(2, 3);
        assertEquals(true, dGraph.contains(1));
        assertEquals(true, dGraph.contains(2));
        assertEquals(true, dGraph.contains(3));
        dGraph.remove(2);
        assertEquals(false, dGraph.contains(2));
        assertEquals(false, dGraph.contains(2, 3));

        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();

        assertEquals(true, uDGraph.contains(1));
        assertEquals(true, uDGraph.contains(2));
        uDGraph.add(1, 2);
        uDGraph.add(2, 4);
        uDGraph.add(1, 3);
        uDGraph.remove(4, 2);
        assertEquals(false, uDGraph.contains(2, 4));
        uDGraph.remove(2);
        assertEquals(false, uDGraph.contains(2));
    }

    @Test
    public void testVertices() {
        DirectedGraph dGraph = new DirectedGraph();

        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();

        Iteration<Integer> iter = dGraph.vertices();
        int count = 1;

        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }

        dGraph.remove(1);

        iter = dGraph.vertices();
        count = 2;
        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }

        UndirectedGraph uDGraph = new UndirectedGraph();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        iter = uDGraph.vertices();
        count = 1;

        while (iter.hasNext()) {
            assertEquals(count, iter.next().intValue());
            count += 1;
        }
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.remove(4);
        iter = uDGraph.vertices();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
        integerArrayList.add(5);
        integerArrayList.add(6);
        integerArrayList.add(7);
        integerArrayList.add(8);

        int i = 0;
        while (iter.hasNext()) {
            assertEquals((int) integerArrayList.get(i), iter.next().intValue());
            count += 1;
            i++;
        }
    }

    @Test
    public void testSuccessors() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(false, dGraph.successors(1).hasNext());
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 1);
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(1, 4);
        dGraph.add(2, 4);
        dGraph.add(2, 3);

        dGraph.add(3, 2);
        dGraph.add(3, 1);

        Iteration<Integer> iter = dGraph.successors(1);
        assertEquals(1, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());
        assertEquals(4, iter.next().intValue());

        iter = dGraph.successors(2);

        assertEquals(3, iter.next().intValue());
        assertEquals(4, iter.next().intValue());
        assertEquals(false, iter.hasNext());

        iter = dGraph.successors(3);

        assertEquals(1, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        assertEquals(false, iter.hasNext());

    }

    @Test
    public void testPredecessors() {
        DirectedGraph dGraph = new DirectedGraph();
        assertEquals(false, dGraph.predecessors(1).hasNext());
        assertEquals(false, dGraph.predecessors(2).hasNext());
        assertEquals(false, dGraph.predecessors(3).hasNext());

        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(5, 5);
        dGraph.add(3, 5);
        dGraph.add(3, 2);
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(2, 3);
        dGraph.add(2, 4);

        Iteration<Integer> iter = dGraph.predecessors(2);

        assertEquals(1, iter.next().intValue());
        assertEquals(3, iter.next().intValue());

        iter = dGraph.predecessors(5);
        assertEquals(3, iter.next().intValue());
        assertEquals(5, iter.next().intValue());

        iter = dGraph.predecessors(3);
        assertEquals(1, iter.next().intValue());
        assertEquals(2, iter.next().intValue());
        iter = dGraph.predecessors(4);
        assertEquals(2, iter.next().intValue());
        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testNeighbors() {
        UndirectedGraph uDGraph = new UndirectedGraph();
        assertEquals(false, uDGraph.neighbors(1).hasNext());
        assertEquals(false, uDGraph.neighbors(2).hasNext());
        assertEquals(false, uDGraph.neighbors(3).hasNext());


        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();

        uDGraph.add(1, 2);
        uDGraph.add(1, 3);
        uDGraph.add(2, 4);
        uDGraph.add(2, 3);
        uDGraph.add(4, 6);

        Iteration<Integer> iter = uDGraph.neighbors(1);
        assertEquals(2, iter.next().intValue());
        assertEquals(3, iter.next().intValue());

        iter = uDGraph.successors(2);
        assertEquals(1, iter.next().intValue());

        assertEquals(3, iter.next().intValue());
        assertEquals(4, iter.next().intValue());

        assertEquals(false, iter.hasNext());
    }

    @Test
    public void testEdges() {
        DirectedGraph dGraph = new DirectedGraph();

        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add();
        dGraph.add(1, 2);
        dGraph.add(1, 3);
        dGraph.add(1, 4);
        dGraph.add(2, 3);
        dGraph.add(2, 4);

        Iteration<int[]> iter = dGraph.edges();
        int[] tmp = iter.next();

        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(4, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(3, tmp[1]);
        tmp = iter.next();
        assertEquals(2, tmp[0]);
        assertEquals(4, tmp[1]);
        assertEquals(false, iter.hasNext());

        UndirectedGraph uDGraph = new UndirectedGraph();

        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add();
        uDGraph.add(1, 2);
        uDGraph.add(1, 2);
        uDGraph.add(2, 1);
        uDGraph.add(1, 3);
        uDGraph.add(3, 1);
        iter = uDGraph.edges();
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(2, tmp[1]);
        tmp = iter.next();
        assertEquals(1, tmp[0]);
        assertEquals(3, tmp[1]);
        assertEquals(false, iter.hasNext());
    }


    @Test
    public void testEdgeId() {
        UndirectedGraph uDGraph2 = new UndirectedGraph();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        uDGraph2.add();
        System.out.println(uDGraph2.add(1, 8));
        System.out.println(uDGraph2.add(8, 1));
    }

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        UndirectedGraph uG = new UndirectedGraph();
        assertEquals(0, uG.vertexSize());
        assertEquals(0, uG.edgeSize());
    }

    @Test
    public void testUndirectedMaxVertex() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();


        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(6, 9);

        assertEquals(12, g.edgeSize());
        assertEquals(13, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(13, g.maxVertex());
        g.remove(13);
        assertEquals(8, g.edgeSize());
        assertEquals(12, g.maxVertex());
        g.remove(6, 9);
        assertEquals(7, g.edgeSize());
        assertEquals(12, g.maxVertex());
        g.remove(7, 3);
        assertEquals(6, g.edgeSize());
        assertEquals(12, g.maxVertex());
    }

    @Test
    public void testUndirectedEdgeSize() {
        UndirectedGraph g = new UndirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(1, 1);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(13, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(5, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(7, 10);
        assertEquals(4, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(8);
        assertEquals(1, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }

    @Test
    public void testDirectedMaxVertex() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1);
        assertEquals(8, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(10, 9);
        assertEquals(6, g.edgeSize());
        assertEquals(9, g.maxVertex());
        g.remove(8, 9);
        assertEquals(5, g.edgeSize());
        assertEquals(9, g.maxVertex());
    }

    @Test
    public void testDirectedEdgeSize() {
        DirectedGraph g = new DirectedGraph();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add();
        g.add(1, 2);
        g.add(1, 3);
        g.add(1, 4);
        g.add(2, 5);
        g.add(2, 3);
        g.add(2, 6);
        g.add(3, 7);
        g.add(3, 8);
        g.add(8, 1);
        g.add(8, 9);
        g.add(8, 10);
        g.add(10, 7);
        assertEquals(12, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(1, 2);
        assertEquals(11, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(2, 5);
        assertEquals(10, g.edgeSize());
        assertEquals(10, g.maxVertex());
        g.remove(10, 7);
        assertEquals(9, g.edgeSize());
        assertEquals(10, g.maxVertex());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testChecktMyVertex1() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.checkMyVertex(1);

        UndirectedGraph uGraph = new UndirectedGraph();
        uGraph.checkMyVertex(2);
        dGraph.add();
        dGraph.checkMyVertex(1);
        dGraph.checkMyVertex(2);
    }

    @Test
    public void testCheckVertexRight() {
        DirectedGraph dGraph = new DirectedGraph();
        dGraph.add();
        dGraph.checkMyVertex(1);
    }

}
