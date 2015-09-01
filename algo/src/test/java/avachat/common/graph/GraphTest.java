package avachat.common.graph;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by avachat on 8/28/15.
 */
public class GraphTest {

    @Test
    public void testAddVerticesAndEdges() {

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
    public void testPerformBreadthFirstTraversal_1() {

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

        // no-op lambda
        Predicate<Vertex<String>> keepGoing = (Vertex<String> v) -> {
            return true;
        };


        BiConsumer<Vertex<String>, List<Edge<String>>> visitCompletedConsumer_a =
                (Vertex<String> v,List<Edge<String>> path) -> {
                    System.out.println("For vertex " + v.getIdStr()  + " Found path " + path);
                    if ( ! v.getId().equals("a")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    return;
                };

        // make assertions after traversal is complete
        Consumer<Map<Vertex<String>, List<Edge<String>>>> traversalCompletedConsumer_a =
                (Map<Vertex<String>, List<Edge<String>>> pathsToVertices) -> {
                    // All vertices must have been visited when starting from a
                    graph.getVertices().forEach((Vertex<String> v) -> Assert.assertTrue(v.isVisited()));
                    // all vertices except a have hop count of 1
                    Assert.assertEquals(graph.getVertex("a").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 1);
                    // all vertices must have path
                    Assert.assertEquals(pathsToVertices.size(), 5);
                };

        System.out.println();
        System.out.println("testPerformBreadthFirstTraversal_1" + "Starting traversal from a");
        graph.performBreadthFirstTraversal(graph.getVertex("a"), visitCompletedConsumer_a, traversalCompletedConsumer_a, keepGoing);

        BiConsumer<Vertex<String>, List<Edge<String>>> visitCompletedConsumer_b =
                (Vertex<String> v,List<Edge<String>> path) -> {
                    System.out.println("For vertex " + v.getIdStr()  + " Found path " + path);
                    Assert.assertFalse( v.getId().equals("a")); // cannot come to a
                    if ( v.getId().equals("c")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("d")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("e")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    return;
                };

        // make assertions after traversal is complete
        Consumer<Map<Vertex<String>, List<Edge<String>>>> traversalCompletedConsumer_b =
                (Map<Vertex<String>, List<Edge<String>>> pathsToVertices) -> {
                    // all vertices except a have hop count of 1
                    Assert.assertEquals(graph.getVertex("a").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 2);
                    // all vertices except a must have path
                    Assert.assertEquals(pathsToVertices.size(), 4);
                    Assert.assertFalse(pathsToVertices.containsKey(graph.getVertex("a")));
                };

        System.out.println();
        System.out.println("testPerformBreadthFirstTraversal_1" + "Starting traversal from b");
        graph.performBreadthFirstTraversal(graph.getVertex("b"), visitCompletedConsumer_b, traversalCompletedConsumer_b, keepGoing);

    }


    /**
     * Test for a more complicated, deeper graph
     */
    @Test
    public void testPerformBreadthFirstTraversal_2() {

        Graph<String> graph = new Graph<>();

        graph.addVerticesAndEdge("a", "b");
        graph.addVerticesAndEdge("a", "c");
        graph.addVerticesAndEdge("a", "d");

        graph.addVerticesAndEdge("b", "c");
        graph.addVerticesAndEdge("b", "d");
        graph.addVerticesAndEdge("b", "e");

        graph.addVerticesAndEdge("c", "a"); // cycle
        graph.addVerticesAndEdge("c", "d");
        graph.addVerticesAndEdge("c", "e");
        graph.addVerticesAndEdge("c", "f");

        graph.addVerticesAndEdge("d", "b"); // cycle
        graph.addVerticesAndEdge("d", "e");
        graph.addVerticesAndEdge("d", "f");
        graph.addVerticesAndEdge("d", "g");

        graph.addVerticesAndEdge("e", "f");
        graph.addVerticesAndEdge("e", "g");
        graph.addVerticesAndEdge("e", "h");

        // no-op lambdas
        Predicate<Vertex<String>> keepGoing = (Vertex<String> v) -> {
            return true;
        };

        BiConsumer<Vertex<String>, List<Edge<String>>> visitCompletedConsumer_a_all =
                (Vertex<String> v,List<Edge<String>> path) -> {
                    System.out.println("For vertex " + v.getIdStr()  + " Found path " + path);
                    if ( v.getId().equals("b")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("c")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("d")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("e")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    if ( v.getId().equals("f")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    if ( v.getId().equals("g")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    if ( v.getId().equals("h")) {
                        Assert.assertEquals(path.size(), 3);
                    }
                    return;
                };

        // make assertions after traversal is complete
        Consumer<Map<Vertex<String>, List<Edge<String>>>> traversalCompletedConsumer_a_all =
                (Map<Vertex<String>, List<Edge<String>>> pathsToVertices) -> {
                    // All vertices must have been visited when starting from a
                    graph.getVertices().forEach((Vertex<String> v) -> Assert.assertTrue(v.isVisited()));
                    Assert.assertEquals(graph.getVertex("a").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 2);
                    Assert.assertEquals(graph.getVertex("f").getHopCount(), 2);
                    Assert.assertEquals(graph.getVertex("g").getHopCount(), 2);
                    Assert.assertEquals(graph.getVertex("h").getHopCount(), 3);
                };

        System.out.println();
        System.out.println("testPerformBreadthFirstTraversal_2" + "Starting traversal from a");
        graph.performBreadthFirstTraversal(graph.getVertex("a"), visitCompletedConsumer_a_all, traversalCompletedConsumer_a_all, keepGoing);



        // starting at b and stopping at f
        // cannot make any predictions if g will be reached or traversal will stop before g
        // but h cannot be visited

        // stop at f
        Predicate<Vertex<String>> keepGoingTillF = (Vertex<String> v) -> {
            return ( ! v.getId().equals("f"));
        };

        BiConsumer<Vertex<String>, List<Edge<String>>> visitCompletedConsumer_b_to_f =
                (Vertex<String> v,List<Edge<String>> path) -> {
                    System.out.println("For vertex " + v.getIdStr() + " Found path " + path);
                    Assert.assertFalse( v.getId().equals("h")); // cannot come to h
                    if ( v.getId().equals("a")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    if ( v.getId().equals("b")) {
                        Assert.assertEquals(path.size(), 0);
                    }
                    if ( v.getId().equals("c")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("d")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("e")) {
                        Assert.assertEquals(path.size(), 1);
                    }
                    if ( v.getId().equals("g")) {
                        // if g is reached at all, then size has to be 2
                        Assert.assertEquals(path.size(), 2);
                    }
                    if ( v.getId().equals("f")) {
                        Assert.assertEquals(path.size(), 2);
                    }
                    return;
                };

        // make assertions after traversal is complete
        Consumer<Map<Vertex<String>, List<Edge<String>>>> traversalCompletedConsumer_b_to_f =
                (Map<Vertex<String>, List<Edge<String>>> pathsToVertices) -> {
                    Assert.assertEquals(graph.getVertex("b").getHopCount(), 0);
                    Assert.assertEquals(graph.getVertex("c").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("d").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("e").getHopCount(), 1);
                    Assert.assertEquals(graph.getVertex("f").getHopCount(), 2);
                    Assert.assertEquals(graph.getVertex("h").getHopCount(), 0);
                    // if g is reached, hop count has to be 2
                    Vertex<String> gVertex = graph.getVertex("g");
                    int gHopCout = gVertex.getHopCount();
                    Assert.assertTrue((gVertex.isVisited()) ? (gHopCout == 2) : (gHopCout == 0));
                    // if a is reached, hop count has to be 2
                    Vertex<String> aVertex = graph.getVertex("a");
                    int aHopCout = aVertex.getHopCount();
                    Assert.assertTrue( (aVertex.isVisited()) ? (aHopCout == 2) : (aHopCout==0));
                };

        System.out.println();
        System.out.println("testPerformBreadthFirstTraversal_2" + "Starting traversal from b");
        graph.performBreadthFirstTraversal(graph.getVertex("b"), visitCompletedConsumer_b_to_f, traversalCompletedConsumer_b_to_f, keepGoingTillF);

    }

}