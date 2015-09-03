package avachat.hackrank;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by avachat on 8/13/15.
 */

/**
 * Stub for starting point.
 */
public class Solution {

    public static class Vertex<IdType extends Comparable<IdType>> {

        private final IdType id;

        /**
         * Edges.
         * NOTE : Data integrity is maintained by the graph object.
         */
        private final Map<Vertex<IdType>, Set<Edge<IdType>>> sources;
        private final Map<Vertex<IdType>, Set<Edge<IdType>>> destinations;

        /**
         * Following variables are useful to traversal algorithms
         */
        private boolean isVisited = false;
        private int hopCount = 0;
        private double cumulativeWight = 0.0;

        public Vertex (IdType id) {

            if ( (id == null) || (id.toString().isEmpty())) {
                throw new IllegalArgumentException("Vertex id cannot be null");
            }

            this.id = id;
            this.sources = new HashMap<Vertex<IdType>, Set<Edge<IdType>>>();
            this.destinations = new HashMap<Vertex<IdType>, Set<Edge<IdType>>>();

            this.isVisited = false;
            this.hopCount = 0;
            this.cumulativeWight = 0.0;
        }

        public void addDestination(Vertex<IdType> destination, Edge<IdType> edge) {



            Set<Edge<IdType>> setEdges = destinations.get(destination);
            if ( setEdges == null ) {
                setEdges = new HashSet<Edge<IdType>>();
                destinations.put(destination, setEdges);
            }

            setEdges.add(edge);

        }

        public void addSource(Vertex<IdType> source, Edge<IdType> edge) {



            Set<Edge<IdType>> setEdges = sources.get(source);
            if ( setEdges == null ) {
                setEdges = new HashSet<Edge<IdType>>();
                sources.put(source, setEdges);
            }

            setEdges.add(edge);

        }

        public boolean isSourceOf (Vertex<IdType> other) {
            return (destinations.containsKey(other));
        }

        public boolean isDestinationOf (Vertex<IdType> other) {
            return (sources.containsKey(other));
        }

        public boolean isConnectedTo (Vertex<IdType> other) {
            return ( isSourceOf(other) || isDestinationOf(other));
        }

        public Set<Edge<IdType>> getSourceEdges() {
            Set<Edge<IdType>> allEdges = new HashSet<Edge<IdType>>();
            for (Set<Edge<IdType>> edgeSet : sources.values()) {
                allEdges.addAll(edgeSet);
            }
            return allEdges;
        }

        public Set<Edge<IdType>> getDestinationEdges() {
            Set<Edge<IdType>> allEdges = new HashSet<Edge<IdType>>();
            for (Set<Edge<IdType>> edgeSet : destinations.values()) {
                allEdges.addAll(edgeSet);
            }
            return allEdges;
        }

        public Set<Vertex<IdType>> getSourceVertices() {
            return (sources.keySet());
        }

        public Set<Vertex<IdType>> getDestinationVertices() {
            return (destinations.keySet());
        }

        public IdType getId() {
            return id;
        }

        public Map<Vertex<IdType>, Set<Edge<IdType>>> getSources() {
            return (sources);
        }

        public Map<Vertex<IdType>, Set<Edge<IdType>>> getDestinations() {
            return (destinations);
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setIsVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }

        public int getHopCount() {
            return hopCount;
        }

        public void setHopCount(int hopCount) {
            this.hopCount = hopCount;
        }

        public double getCumulativeWight() {
            return cumulativeWight;
        }

        public void setCumulativeWight(double cumulativeWight) {
            this.cumulativeWight = cumulativeWight;
        }

        public int incrementHopCount() {
            return ++hopCount;
        }

        public double addToCumulativeWeight(double forwardWeight) {
            cumulativeWight += forwardWeight;
            return cumulativeWight;
        }

        /**
         * Helper
         * @return
         */
        public String getIdStr() {
            return id.toString();
        }

        @Override
        public boolean equals (Object obj) {

            if ( this == obj ) {
                return true;
            }

            if ( obj == null ) {
                return false;
            }

            if ( !(obj instanceof Vertex) ) {
                return false;
            }

            Vertex<IdType> other = (Vertex<IdType>) obj;

            return Objects.equals(this.getId(), other.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        @Override
        public String toString() {
            return getIdStr();
        }

    }


    /**
     * TODO : Implement Comparable interface directly in Vertex.
     *
     * Compares based on IDs
     */
    public static class VertexIdComparator<IdType extends Comparable<IdType>> implements Comparator<Vertex<IdType>> {

        public int compare(Vertex<IdType> o1, Vertex<IdType> o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }


    public static class Edge<IdType extends Comparable<IdType>> {

        protected final boolean isDirectional;
        protected final Vertex<IdType> source;
        protected final Vertex<IdType> destination;
        protected final double forwardWeight;
        protected final double backwardWeight;

        private boolean isVisited = false;

        /**
         * Creates an directional edge
         * NOTE : DOES NOT MODIFY THE VERTEX
         * Those integrity constraints are imposed by the Graph object
         *
         * @param source
         * @param destination
         */
        public Edge(Vertex<IdType> source, Vertex<IdType> destination, boolean isDirectional, double forwardWeight, double backwardWeight) {

            // This implementation allows self edge, source and vertex can be same.

            this.isDirectional = isDirectional;
            this.source = source;
            this.destination = destination;
            this.forwardWeight = forwardWeight;
            this.backwardWeight = backwardWeight;
            this.isVisited = false;
        }

        /**
         * Helper construtor
         *
         * @param source
         * @param destination
         */
        public Edge (Vertex<IdType> source, Vertex<IdType> destination) {
            this (source, destination, true, 1.0, 1.0);
        }

        /**
         * Helper construtor
         *
         * @param source
         * @param destination
         */
        public Edge (Vertex<IdType> source, Vertex<IdType> destination, boolean isDirectional) {
            this (source, destination, isDirectional, 1.0, 1.0);
        }

        @Override
        public boolean equals (Object obj) {

            if (obj == null) {
                return false;
            }

            if ( ! (obj instanceof Edge)) {
                return false;
            }

            Edge other = (Edge) obj;

            if (this == other) {
                return true;
            }

            return ( (this.source.equals(other.source))
                    && (this.destination.equals(other.destination)));
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination);
        }

        @Override
        public String toString() {
            return "(" + source + "->" + destination + ")";
        }

        public boolean isDirectional() {
            return isDirectional;
        }

        public Vertex<IdType> getSource() {
            return source;
        }

        public Vertex<IdType> getDestination() {
            return destination;
        }

        public double getForwardWeight() {
            return forwardWeight;
        }

        public double getBackwardWeight() {
            return backwardWeight;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setIsVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }


    }

    public static class UndirectedEdge<IdType extends Comparable<IdType>> extends Edge<IdType> {

        //
        // Just diff names for source and destinations based on their natural sorting order
        protected final Vertex<IdType> lowerVertex, upperVertex;

        /**
         * Creates an undirectional edge
         *
         * @param source
         * @param destination
         * @param forwardWeight
         * @param backwardWeight
         */
        public UndirectedEdge(Vertex<IdType> source, Vertex<IdType> destination, double forwardWeight, double backwardWeight) {

            super(source, destination, false, forwardWeight, backwardWeight);

            // Self edges are not allowed
            VertexIdComparator<IdType> vertexIdComparator = new VertexIdComparator<IdType>();

            if (vertexIdComparator.compare(source, destination) == 0) {
                throw new IllegalArgumentException("Source and destination cannot be same");
            }

            if ( vertexIdComparator.compare(this.source, this.destination) < 0) {
                lowerVertex = source;
                upperVertex = destination;
            } else {
                upperVertex = source;
                lowerVertex = destination;
            }
        }


        /**
         * Helper constructor
         *
         * @param source
         * @param destination
         */
        public UndirectedEdge(Vertex<IdType> source, Vertex<IdType> destination) {
            this(source, destination, 1.0, 1.0);
        }

        @Override
        public boolean equals (Object obj) {

            if (obj == null) {
                return false;
            }

            if ( ! (obj instanceof UndirectedEdge)) {
                return false;
            }

            UndirectedEdge<IdType> other = (UndirectedEdge<IdType>) obj;

            if (this == other) {
                return true;
            }

            return ( (this.lowerVertex.equals(other.lowerVertex)) && (this.upperVertex.equals(other.upperVertex)));
        }

        @Override
        public int hashCode() {
            return Objects.hash(lowerVertex, upperVertex);
        }


        @Override
        public String toString() {
            return "(" + source + "--" + destination + ")";
        }
    }

    public static class Graph<IdType extends Comparable<IdType>> {

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

            List<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>> neighborsList = new ArrayList<SimpleImmutableEntry<Vertex<IdType>, Edge<IdType>>>();

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
            return vertices;
        }

        public Set<Edge<IdType>> getEdges() {
            return (edges);
        }
    }

    public static class UndirectedGraph<IdType extends Comparable<IdType>> extends Graph<IdType> {

        public UndirectedGraph() {
            super();
        }

        @Override
        protected Edge<IdType> createGraphSpecificEdge(Vertex<IdType> source, Vertex<IdType> destination) {
            return new UndirectedEdge<IdType>(source, destination);
        }


        /**
         *
         * @param vertex
         * @return
         */
        protected Map<Vertex<IdType>, Set<Edge<IdType>>> getGraphSpecificDestinations (Vertex<IdType> vertex) {

            // combine source and destinations
            // it's safe to do a putAll, as only one edge between source and vertex will exist
            // there is no danger of overwriting
            // The UndirectedEdge equals/compare etc ensure that the graph treats edges from A-B and B-A are treated as same
            // A multi-graph with undirected edges can have multiple edges between two vertices, and will have to
            // implement this function
            Map<Vertex<IdType>, Set<Edge<IdType>>> neighborsMap = new HashMap<>(vertex.getDestinations());
            neighborsMap.putAll(vertex.getSources());

            //System.out.println("For vertex " + vertex + " undirected edges are " + neighborsMap);
            return neighborsMap;
        }

    }


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

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numTestCases = scanner.nextInt();

        for (int t = 0; t < numTestCases; t++) {

            solveTestCaseBFS_1(scanner);
        }

    }
}
