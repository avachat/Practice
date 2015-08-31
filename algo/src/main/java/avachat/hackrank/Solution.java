package avachat.hackrank;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by avachat on 8/13/15.
 */

/**
 * Stub for starting point.
 */
public class Solution {

    public class Edge<IdType extends Comparable<IdType>> {

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

            Preconditions.checkNotNull(source, "source for an edge cannot be null");
            Preconditions.checkNotNull(destination, "destination for an edge cannot be null");

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
        public Edge(Vertex<IdType> source, Vertex<IdType> destination) {
            this(source, destination, false, 1.0, 1.0);
        }

        /**
         * Helper construtor
         *
         * @param source
         * @param destination
         */
        public Edge(Vertex<IdType> source, Vertex<IdType> destination, boolean isDirectional) {
            this(source, destination, isDirectional, 1.0, 1.0);
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if (!(obj instanceof Edge)) {
                return false;
            }

            Edge other = (Edge) obj;

            if (this == other) {
                return true;
            }

            return ((this.source.equals(other.source))
                    && (this.destination.equals(other.destination)));
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination);
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

    public class UndirectedEdge<IdType extends Comparable<IdType>> extends Edge<IdType> {

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
            Preconditions.checkArgument(!(source.equals(destination)), "Source and vertex cannot be same");

            VertexIdComparator<IdType> vertexIdComparator = new VertexIdComparator<IdType>();

            if (vertexIdComparator.compare(source, destination) == 0) {
                throw new IllegalArgumentException("Source and destination cannot be same");
            }

            if (vertexIdComparator.compare(this.source, this.destination) < 0) {
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
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if (!(obj instanceof UndirectedEdge)) {
                return false;
            }

            UndirectedEdge<IdType> other = (UndirectedEdge<IdType>) obj;

            if (this == other) {
                return true;
            }

            return ((this.lowerVertex.equals(other.lowerVertex)) && (this.upperVertex.equals(other.upperVertex)));
        }

        @Override
        public int hashCode() {
            return Objects.hash(lowerVertex, upperVertex);
        }

    }


    public class Vertex<IdType extends Comparable<IdType>> {

        private final IdType id;

        /**
         * Edges.
         * NOTE : Data integrity is maintained by the graph object.
         */
        private final Map<Vertex<IdType>, Set<Edge<IdType>>> sources;
        private final Map<Vertex<IdType>, Set<Edge<IdType>>> destinations;

        private boolean isVisited = false;

        public Vertex(IdType id) {

            if ((id == null) || (id.toString().isEmpty())) {
                throw new IllegalArgumentException("Vertex id cannot be null");
            }

            this.id = id;
            this.sources = new HashMap<Vertex<IdType>, Set<Edge<IdType>>>();
            this.destinations = new HashMap<Vertex<IdType>, Set<Edge<IdType>>>();

            this.isVisited = false;
        }

        public void addDestination(Vertex<IdType> destination, Edge<IdType> edge) {

            Preconditions.checkNotNull(destination, "null param destination");
            Preconditions.checkNotNull(edge, "null param edge");

            Preconditions.checkArgument(edge.getSource().equals(this));
            Preconditions.checkArgument(edge.getDestination().equals(destination));

            Set<Edge<IdType>> setEdges = destinations.get(destination);
            if (setEdges == null) {
                setEdges = new HashSet<Edge<IdType>>();
                destinations.put(destination, setEdges);
            }

            Preconditions.checkArgument(!setEdges.contains(edge), "Cannot add duplicate edge");
            setEdges.add(edge);

        }

        public void addSource(Vertex<IdType> source, Edge<IdType> edge) {

            Preconditions.checkNotNull(source, "null param destination");
            Preconditions.checkNotNull(edge, "null param edge");

            Preconditions.checkArgument(edge.getDestination().equals(this));
            Preconditions.checkArgument(edge.getSource().equals(source));

            Set<Edge<IdType>> setEdges = sources.get(source);
            if (setEdges == null) {
                setEdges = new HashSet<Edge<IdType>>();
                sources.put(source, setEdges);
            }

            Preconditions.checkArgument(!setEdges.contains(edge), "Cannot add duplicate edge");
            setEdges.add(edge);

        }

        public boolean isSourceOf(Vertex<IdType> other) {
            return (destinations.containsKey(other));
        }

        public boolean isDestinationOf(Vertex<IdType> other) {
            return (sources.containsKey(other));
        }

        public boolean isConnectedTo(Vertex<IdType> other) {
            return (isSourceOf(other) || isDestinationOf(other));
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (!(obj instanceof Vertex)) {
                return false;
            }

            Vertex<IdType> other = (Vertex<IdType>) obj;

            return Objects.equals(this.getId(), other.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public IdType getId() {
            return id;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setIsVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }

        public Set<Vertex<IdType>> getSourceVertices() {
            return sources.keySet();
        }

        public Set<Vertex<IdType>> getDestinationVertices() {
            return destinations.keySet();
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

        /**
         * TODO : Implement Comparable interface directly in Vertex.
         * <p/>
         * Helper
         *
         * @return
         */
        public String getIdStr() {
            return id.toString();
        }

    }

    /**
     * Compares based on IDs
     */
    public class VertexIdComparator<IdType extends Comparable<IdType>> implements Comparator<Vertex<IdType>> {

        public int compare(Vertex<IdType> o1, Vertex<IdType> o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }


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
         * Allow derived classes to override this method
         * While this graph creates only a directional edge.
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

        public SortedSet<Vertex<IdType>> getVertices() {
            return ImmutableSortedSet.copyOf(vertexComparator, vertices);
        }

        public Set<Edge<IdType>> getEdges() {
            return ImmutableSet.copyOf(edges);
        }
    }


    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numTestCases = scanner.nextInt();

        for (int t = 0; t < numTestCases; t++) {

            int num = scanner.nextInt();

            long result = 0;

            System.out.println(result);
        }

    }
}
