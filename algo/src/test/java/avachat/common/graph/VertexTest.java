package avachat.common.graph;

import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO : Add test cases for multi-graphs. (This is blocked by implementation of multi-edge)
 *
 * Created by avachat on 8/27/15.
 */
public class VertexTest {

    @Test
    public void testEquals () {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_a_2 = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");

        Assert.assertEquals(vertex_a, vertex_a_2);
        Assert.assertNotEquals(vertex_a, vertex_b);
    }

    @Test
    public void testAddDestinationSimple() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_a_b = new Edge<String>(vertex_a, vertex_b);
        Edge<String> edge_a_c = new Edge<String>(vertex_a, vertex_c);

        vertex_a.addDestination(vertex_b, edge_a_b);
        vertex_a.addDestination(vertex_c, edge_a_c);

        Assert.assertTrue(vertex_a.isConnectedTo(vertex_b));
        Assert.assertTrue(vertex_a.isConnectedTo(vertex_c));
        Assert.assertTrue(vertex_a.isSourceOf(vertex_b));
        Assert.assertTrue(vertex_a.isSourceOf(vertex_c));

        Set<Edge<String>> edgeSet = new HashSet<Edge<String>>();
        edgeSet.add(edge_a_b);
        edgeSet.add(edge_a_c);
        Assert.assertEquals(edgeSet, vertex_a.getDestinationEdges());

        Set<Vertex<String>> vertexSet = new HashSet<Vertex<String>>();
        vertexSet.add(vertex_b);
        vertexSet.add(vertex_c);
        Assert.assertEquals(vertexSet, vertex_a.getDestinationVertices());
    }

    @Test
    public void testAddSourceSimple() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_b_a = new Edge<String>(vertex_b, vertex_a);
        Edge<String> edge_c_a = new Edge<String>(vertex_c, vertex_a);

        vertex_a.addSource(vertex_b, edge_b_a);
        vertex_a.addSource(vertex_c, edge_c_a);

        Assert.assertTrue(vertex_a.isConnectedTo(vertex_b));
        Assert.assertTrue(vertex_a.isConnectedTo(vertex_c));
        Assert.assertTrue(vertex_a.isDestinationOf(vertex_b));
        Assert.assertTrue(vertex_a.isDestinationOf(vertex_c));

        Set<Edge<String>> edgeSet = new HashSet<Edge<String>>();
        edgeSet.add(edge_b_a);
        edgeSet.add(edge_c_a);
        Assert.assertEquals(edgeSet, vertex_a.getSourceEdges());

        Set<Vertex<String>> vertexSet = new HashSet<Vertex<String>>();
        vertexSet.add(vertex_b);
        vertexSet.add(vertex_c);
        Assert.assertEquals(vertexSet, vertex_a.getSourceVertices());
    }

    @Test (expectedExceptions = Exception.class)
    public void testAddDestinationWithWrongEdgeSource() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_c_b = new Edge<String>(vertex_c, vertex_b);

        vertex_a.addDestination(vertex_b, edge_c_b);
    }

    @Test (expectedExceptions = Exception.class)
    public void testAddDestinationWithWrongEdgeDestination() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_a_c = new Edge<String>(vertex_a, vertex_c);

        vertex_a.addDestination(vertex_b, edge_a_c);
    }

    @Test (expectedExceptions = Exception.class)
    public void testAddSourceWithWrongEdgeDestination() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_b_c = new Edge<String>(vertex_b, vertex_c);

        vertex_a.addSource(vertex_b, edge_b_c);
    }

    @Test (expectedExceptions = Exception.class)
    public void testAddSourceWithWrongEdgeSource() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");
        Vertex<String> vertex_c = new Vertex<String>("c");

        Edge<String> edge_c_a = new Edge<String>(vertex_c, vertex_a);

        vertex_a.addSource(vertex_b, edge_c_a);
    }



}