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
import java.util.function.BiConsumer;
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
     * NOTE : keepGoing.test will be called AFTER visitCompletedConsumer.accept is called
     *  @param startVertex
     * @param visitCompletedConsumer
     * @param keepGoing
     */
    public Map<Vertex<IdType>, List<Edge<IdType>>> performBreadthFirstTraversal(Vertex<IdType> startVertex,
                                                                                BiConsumer<Vertex<IdType>, List<Edge<IdType>>> visitCompletedConsumer,
                                                                                Consumer<Map<Vertex<IdType>, List<Edge<IdType>>>> traversalCompletedConsumer,
                                                                                Predicate<Vertex<IdType>> keepGoing) {

        Preconditions.checkArgument(vertices.contains(startVertex), "Start vertex not found");

        // start with a clean slate
        resetTraversalInfo();

        // destination vertices and their paths
        Map<Vertex<IdType>, List<Edge<IdType>>> allPathsFromStartingVertex = new HashMap<>();

        // vertices to traverse from, in desired order
        // with paths to them from starting vertex
        Queue<Vertex<IdType>> verticesToTraverse = new LinkedList<>();

        // visit the start vertex
        startVertex.setIsVisited(true);
        startVertex.setHopCount(0);
        startVertex.setCumulativeWight(0.0);
        // should stop right now?
        if ( ! keepGoing.test(startVertex)) {
            traversalCompletedConsumer.accept(allPathsFromStartingVertex);
            // end with a clean slate
            resetTraversalInfo();
            return allPathsFromStartingVertex;
        }

        // init the queue with start vertex
        verticesToTraverse.add(startVertex);
        // path to start vertex from start vertex is obviously empty
        allPathsFromStartingVertex.put (startVertex, new ArrayList<Edge<IdType>>());

        boolean doneTraversing = false;
        while ( (!verticesToTraverse.isEmpty()) && ( !doneTraversing)) {

            //System.out.println("Current queue is " + verticesToTraverse);

            // get the first vertex out of the queue
            Vertex currentVertex = verticesToTraverse.remove();
            //System.out.println("Current vertex is " + currentVertex.getIdStr());

            // path to the currentVertex, will be used for neighbors
            List<Edge<IdType>> pathToCurrentVertex = allPathsFromStartingVertex.get(currentVertex);

            // get destinationPairs, and if they are not visited yet, add them to the queue
            List<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>> destinationPairs = getSortedGraphSpecificDestinations(currentVertex);
            for (SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>> destinationPair : destinationPairs) {

                Vertex<IdType> destinationVertex = destinationPair.getKey();
                Edge<IdType> destinationEdge = destinationPair.getValue();
                //System.out.println("----- looking at destination " + destinationVertex.getIdStr() + " isVisited=" + destinationVertex.isVisited() + " from edge " + destinationEdge.toString());

                if (destinationVertex.isVisited()) {
                    continue;
                }

                // visit the vertex
                destinationVertex.setIsVisited(true);
                destinationVertex.setHopCount(currentVertex.getHopCount() + 1);
                destinationVertex.addToCumulativeWeight(destinationEdge.getForwardWeight());

                // update the path to the visited vertex
                List<Edge<IdType>> pathToDestinationVertex = new ArrayList<>();
                pathToDestinationVertex.addAll(pathToCurrentVertex); // path from starting vertex
                pathToDestinationVertex.add(destinationEdge); // append with the edge used to traverse
                // add the path to all paths
                allPathsFromStartingVertex.put (destinationVertex, pathToDestinationVertex);

                // note the completion of visit
                visitCompletedConsumer.accept(destinationVertex, pathToDestinationVertex);

                // should the traversal stop at this vertex
                if ( ! keepGoing.test(destinationVertex)) {
                    doneTraversing = true;
                    break;
                }

                verticesToTraverse.add(destinationVertex);
            }
        }

        traversalCompletedConsumer.accept(allPathsFromStartingVertex);

        // end with a clean slate
        resetTraversalInfo();

        return allPathsFromStartingVertex;

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
    protected List<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>> getSortedGraphSpecificDestinations(Vertex<IdType> vertex) {

        List neighborsList = new ArrayList<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>>();

        Map<Vertex<IdType>, Set<Edge<IdType>>> neighborsMap = getGraphSpecificDestinations(vertex);

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
     * Return only proper destinations.
     *
     * @param vertex
     * @return
     */
    protected Map<Vertex<IdType>, Set<Edge<IdType>>> getGraphSpecificDestinations (Vertex<IdType> vertex) {
        return vertex.getDestinations();
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
