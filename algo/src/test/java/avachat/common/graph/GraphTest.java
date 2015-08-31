package avachat.common.graph;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by avachat on 8/28/15.
 */
public class GraphTest {

    @Test
    public void testAddVerticesAndEdges () {

        Graph<String> graph = new Graph<String>();

        graph.addVerticesAndEdge("a", "b");
        graph.addVerticesAndEdge("a", "c");
        graph.addVerticesAndEdge("a", "d");
        graph.addVerticesAndEdge("a", "e");

        graph.addVerticesAndEdge("b", "c");
        graph.addVerticesAndEdge("b", "d");

        graph.addVerticesAndEdge("c", "b");
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

        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("a"), new Vertex<String>("b"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("a"), new Vertex<String>("c"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("a"), new Vertex<String>("d"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("a"), new Vertex<String>("e"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("b"), new Vertex<String>("c"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("b"), new Vertex<String>("d"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("c"), new Vertex<String>("b"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("c"), new Vertex<String>("e"))));
        Assert.assertTrue(graph.getEdges().contains(new Edge<String>(new Vertex<String>("d"), new Vertex<String>("e"))));

        Assert.assertTrue(graph.getVertex("a").isSourceOf(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("a").isSourceOf(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("a").isSourceOf(new Vertex<String>("d")));
        Assert.assertTrue(graph.getVertex("a").isSourceOf(new Vertex<String>("e")));
        Assert.assertTrue(graph.getVertex("b").isSourceOf(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("b").isSourceOf(new Vertex<String>("d")));
        Assert.assertTrue(graph.getVertex("c").isSourceOf(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("c").isSourceOf(new Vertex<String>("e")));
        Assert.assertTrue(graph.getVertex("d").isSourceOf(new Vertex<String>("e")));

        Assert.assertTrue(graph.getVertex("b").isDestinationOf(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("c").isDestinationOf(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("d").isDestinationOf(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("e").isDestinationOf(new Vertex<String>("a")));
        Assert.assertTrue(graph.getVertex("c").isDestinationOf(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("d").isDestinationOf(new Vertex<String>("b")));
        Assert.assertTrue(graph.getVertex("b").isDestinationOf(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("e").isDestinationOf(new Vertex<String>("c")));
        Assert.assertTrue(graph.getVertex("e").isDestinationOf(new Vertex<String>("d")));

    }


    @Test
    public void testPerformBreadthFirstTraversal_1 () {

        Graph<String> testGraph = new Graph<String>();

        testGraph.addVerticesAndEdge("a", "b");
        testGraph.addVerticesAndEdge("a", "c");
        testGraph.addVerticesAndEdge("a", "d");
        testGraph.addVerticesAndEdge("a", "e");

        testGraph.addVerticesAndEdge("b", "c");
        testGraph.addVerticesAndEdge("b", "d");

        testGraph.addVerticesAndEdge("c", "b");
        testGraph.addVerticesAndEdge("c", "e");

        testGraph.addVerticesAndEdge("d", "e");

        // no-op lambdas
        Predicate<Vertex<String>> keepGoing = (Vertex<String> v) -> { return true; } ;
        Consumer<Vertex<String>> visitCompletedConsumer =
                (Vertex<String> v) -> {
                    System.out.println ("Completed visiting vertex " + v.getIdStr());
                    return;
                } ;

        // make assertions after traversal is complete
        Consumer<Graph<String>> traversalCompletedConsumer_a =
                (Graph<String> graph) -> {
                    // All vertices must have been visited when starting from a
                    graph.getVertices().forEach( (Vertex<String> v) -> Assert.assertTrue(v.isVisited()));
                    // all vertices except a have hop count of 1
                    Assert.assertEquals(graph.getVertex("a").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 1);
                };

        testGraph.performBreadthFirstTraversal( testGraph.getVertex("a"), visitCompletedConsumer, traversalCompletedConsumer_a, keepGoing);

        // make assertions after traversal is complete
        Consumer<Graph<String>> traversalCompletedConsumer_b =
                (Graph<String> graph) -> {
                    // all vertices except a have hop count of 1
                    Assert.assertEquals(graph.getVertex("a").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 1);
                };

        testGraph.performBreadthFirstTraversal( testGraph.getVertex("b"), visitCompletedConsumer, traversalCompletedConsumer_b, keepGoing);

    }

}