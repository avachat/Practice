package avachat.hackrank.graph;

import avachat.common.graph.Edge;
import avachat.common.graph.UndirectedGraph;
import avachat.common.graph.Vertex;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * For history.
 *
 * Created by avachat on 9/2/15.
 */
@SuppressWarnings("unused")
public class Solution {





/**
 * NOTE :
 *
 * 1.
 * There can be more vertices than the edges specify,as some vertices may not be reachable.
 * So vertices have to be added explicitly. This is important.
 *
 * 2.
 * Vertices start with 1. Small detail, but matters.
 *
 * 3.
 * This one is really bad.
 * The input can have duplicate edges.
 * My implementation throws exception while adding such edges.
 * Hence these exceptions have to be caught and ignored.
 *
 * @param scanner
 */
    public static void solveTestCaseBFS_1 (Scanner scanner) {

        UndirectedGraph<Integer> graph = new UndirectedGraph<>();

        int numVertices = scanner.nextInt();

        for (int i = 1 ; i<= numVertices; i++) {
            graph.addVertexIfNeeded(new Integer(i));
        }

        int numEdges = scanner.nextInt();

        for ( int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            try {
                graph.addVerticesAndEdge(new Integer(source), new Integer(destination));
            } catch (IllegalArgumentException ignore) {
            }
        }

        int start = scanner.nextInt();

        Predicate<Vertex<Integer>> keepGoing = (Vertex<Integer> v) -> {
            return true;
        };

        BiConsumer<Vertex<Integer>, List<Edge<Integer>>> visitCompletedConsumer =
                (Vertex<Integer> v,List<Edge<Integer>> path) -> {
                    return;
                };

        Consumer<Map<Vertex<Integer>, List<Edge<Integer>>>> traversalCompletedConsumer =
                (Map<Vertex<Integer>, List<Edge<Integer>>> pathsToVertices) -> {
                    return;
                };

        Map<Vertex<Integer>, List<Edge<Integer>>> paths
                = graph.performBreadthFirstTraversal(
                    graph.getVertex(new Integer(start)),
                            visitCompletedConsumer,
                            traversalCompletedConsumer,
                            keepGoing);

        SortedSet<Vertex<Integer>> vertices = graph.getVertices();

        for ( Vertex<Integer> vertex : vertices) {
            List<Edge<Integer>> path = paths.get(vertex);
            if ( vertex.equals(graph.getVertex(start))) {
                continue;
            }
            else if ( (path == null) || (path.size() == 0)) {
                System.out.print ("-1 ");
            }
            else {
                System.out.print( (6 * path.size()) + " ");
            }
        }
        System.out.println();
    }
}
