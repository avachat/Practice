package avachat.common.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by avachat on 8/28/15.
 */
public class UndirectedGraphTest {

    @Test
    public void testAddVerticesAndEdges () {

        UndirectedGraph<String> graph = new UndirectedGraph<String>();

        graph.addVerticesAndEdge("a", "b");
        graph.addVerticesAndEdge("a", "c");
        graph.addVerticesAndEdge("a", "d");
        graph.addVerticesAndEdge("a", "e");

        graph.addVerticesAndEdge("b", "c");
        graph.addVerticesAndEdge("b", "d");

        graph.addVerticesAndEdge("c", "e");
        graph.addVerticesAndEdge("d", "e");

        Assert.assertTrue(graph.getVertices().contains(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertices().contains(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertices().contains(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertices().contains(new Vertex<String>("d")));
        Assert.assertTrue(graph.getVertices().contains(new Vertex<String>("e")));

        Assert.assertEquals(graph.getVertex("a"), new Vertex<String>("a"));
        Assert.assertEquals(graph.getVertex("b"), new Vertex<String>("b"));
        Assert.assertEquals(graph.getVertex("c"), new Vertex<String>("c"));
        Assert.assertEquals(graph.getVertex("d"), new Vertex<String>("d"));
        Assert.assertEquals(graph.getVertex("e"), new Vertex<String>("e"));

        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("a"), new Vertex<String>("b"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("a"), new Vertex<String>("c"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("a"), new Vertex<String>("d"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("a"), new Vertex<String>("e"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("b"), new Vertex<String>("c"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("b"), new Vertex<String>("d"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("c"), new Vertex<String>("b"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("c"), new Vertex<String>("e"))));
        Assert.assertTrue(graph.getEdges().contains(new UndirectedEdge<String>(new Vertex<String>("d"), new Vertex<String>("e"))));


        Assert.assertTrue(graph.getVertex("a").isConnectedTo(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("a").isConnectedTo(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("a").isConnectedTo(new Vertex<String>("d")));
        Assert.assertTrue(graph.getVertex("a").isConnectedTo(new Vertex<String>("e")));
        Assert.assertTrue(graph.getVertex("b").isConnectedTo(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("b").isConnectedTo(new Vertex<String>("d")));
        Assert.assertTrue(graph.getVertex("c").isConnectedTo(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("c").isConnectedTo(new Vertex<String>("e")));
        Assert.assertTrue(graph.getVertex("d").isConnectedTo(new Vertex<String>("e")));

        Assert.assertTrue(graph.getVertex("b").isConnectedTo(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("c").isConnectedTo(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("d").isConnectedTo(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("e").isConnectedTo(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("c").isConnectedTo(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("d").isConnectedTo(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("b").isConnectedTo(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("e").isConnectedTo(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("e").isConnectedTo(new Vertex<String>("d")));

    }

}