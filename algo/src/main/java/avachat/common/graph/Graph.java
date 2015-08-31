package avachat.common.graph;

import avachat.common.graph.Vertex.VertexIdComparator;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Models a general purpose graph, even multi-graph.
 * <p/>
 * Some methods need to be over-ridden for appropriate behavior.
 * The default behavior is directional simple graph.
 * <p/>
 * <p/>
 * Created by avachat on 8/19/15.
 */
public class Graph<IdType extends Comparable<IdType>> {

    /**
     * These are redundant structures, useful in different usecases.
     */
    protected final Map<IdType, Vertex<IdType>> mapIdVertex;
    protected final SortedSet<Vertex<IdType>> vertices;
    protected final Set<Edge<IdType>> edges;
    protected final Comparator<? super Vertex<IdType>> vertexComparator;

    /**
     * Default graph behaviour.
     * Uses vertex comparison based on ID
     */
    public Graph() {
        this.vertexComparator = new VertexIdComparator<IdType>();
        this.mapIdVertex = new HashMap<IdType, Vertex<IdType>>();
        this.vertices = new TreeSet<Vertex<IdType>>(new VertexIdComparator<IdType>());
        this.edges = new HashSet<Edge<IdType>>();
    }

    protected Vertex<IdType> addVertexIfNeeded(IdType id) {

        if (mapIdVertex.containsKey(id)) {
            return mapIdVertex.get(id);
        }

        Vertex<IdType> vertex = new Vertex<IdType>(id);
        mapIdVertex.put(id, vertex);
        vertices.add(vertex);

        return vertex;
    }

    public Edge<IdType> addVerticesAndEdge(IdType sourceId, IdType destinationId) {

        Vertex<IdType> source = addVertexIfNeeded(sourceId);
        Vertex<IdType> destination = addVertexIfNeeded(destinationId);

        Edge<IdType> edge = createGraphSpecificEdge(source, destination);

        if (edges.contains(edge)) {
            throw new IllegalArgumentException("The required edge already exists");
        }

        edges.add(edge);
        source.addDestination(destination, edge);
        destination.addSource(source, edge);

        return edge;
    }


    /**
     * Not thread safe
     * <p/>
     * NOTE : keepGoing.test will be called before visitCompletedConsumer.accept is called
     *
     * @param startVertex
     * @param visitCompletedConsumer
     * @param keepGoing
     */
    public void performBreadthFirstTraversal(Vertex<IdType> startVertex,
                                      Consumer<Vertex<IdType>> visitCompletedConsumer,
                                      Consumer<Graph<IdType>> traversalCompletedConsumer,
                                      Predicate<Vertex<IdType>> keepGoing) {

        Preconditions.checkArgument(vertices.contains(startVertex), "Start vertex not found");

        resetTraversalInfo();

        // vertices to visit, in the order
        Queue<Vertex<IdType>> verticesToVisit = new LinkedList<>();
        // init with start vertex
        verticesToVisit.add(startVertex);

        while (!verticesToVisit.isEmpty()) {

            // get the first vertex out of the queue
            Vertex currentVertex = verticesToVisit.remove();

            // should stop at this vertex?
            if (!keepGoing.test(currentVertex)) {
                break;
            }

            // set the flag as visited, so even if there is a self edge, it will not add the same vertex again
            currentVertex.setIsVisited(true);

            // get destinationPairs, and if they are not visited yet, add them to the queue
            List<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>> destinationPairs = getGraphSpecificDestinations(currentVertex);
            for (SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>> destinationPair : destinationPairs) {

                Vertex<IdType> destinationVertex = destinationPair.getKey();
                Edge<IdType> destinationEdge = destinationPair.getValue();

                if (destinationVertex.isVisited()) {
                    continue;
                }

                destinationVertex.setIsVisited(true);
                destinationVertex.incrementHopCount();
                destinationVertex.addToCumulativeWeight(destinationEdge.getForwardWeight());
                verticesToVisit.add(destinationVertex);
            }

            // note the completion of visit
            visitCompletedConsumer.accept(currentVertex);
        }

        traversalCompletedConsumer.accept(this);

        resetTraversalInfo();

    }


    /**
     * Allow derived classes to override this method
     *
     * This graph only looks at the proper destinations.
     * A bidirectional graph will need to look at both source and destination edges.
     *
     * It will just take the first edge for the vertex, and use that to return a pair.
     * Such an approach works fine for simple graph.
     *
     * This graph does not return the vertices in any particular order.
     * Which may be needed for some implementation.
     *
     * @param vertex
     * @return
     */
    protected List<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>> getGraphSpecificDestinations(Vertex<IdType> vertex) {

        List neighborsList = new ArrayList<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>>();

        Map<Vertex<IdType>, Set<Edge<IdType>>> neighborsMap = vertex.getDestinations();

        for ( Vertex<IdType> neighbor : neighborsMap.keySet()) {
            // pick the first edge
            Edge<IdType> edge = neighborsMap.get(neighbor).iterator().next();
            neighborsList.add ( new SimpleImmutableEntry<>(neighbor, edge));
        }

        return neighborsList;
    }


    /**
     * Allow derived classes to override this method
     *
     * This graph creates only a directional edge.
     *
     * @param source
     * @param destination
     * @return
     */
    protected Edge<IdType> createGraphSpecificEdge(Vertex<IdType> source, Vertex<IdType> destination) {
        return new Edge<IdType>(source, destination, true);
    }

    public Vertex<IdType> getVertex(IdType id) {
        return mapIdVertex.get(id);
    }

    public void resetTraversalInfo() {
        clearAllHopCounts();
        clearAllCumulativeWeights();
        clearAllVisitedEdgesAndVertices();
    }

    public void clearAllVisitedEdgesAndVertices() {
        clearAllVisitedEdges();
        clearAllVisitedVertices();
    }

    public void clearAllVisitedEdges() {
        edges.forEach(e -> e.setIsVisited(false));
    }

    public void clearAllVisitedVertices() {
        vertices.forEach(v -> v.setIsVisited(false));
    }

    public void clearAllHopCounts() {
        vertices.forEach(v -> v.setHopCount(0));
    }

    public void clearAllCumulativeWeights() {
        vertices.forEach(v -> v.setCumulativeWight(0.0));
    }

    public SortedSet<Vertex<IdType>> getVertices() {
        return ImmutableSortedSet.copyOf(vertexComparator, vertices);
    }

    public Set<Edge<IdType>> getEdges() {
        return ImmutableSet.copyOf(edges);
    }
}
