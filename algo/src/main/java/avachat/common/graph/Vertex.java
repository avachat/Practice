package avachat.common.graph;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This Vertex object can be used in all types of graphs.
 * Directional, bidirectional, including multi-graphs.
 * The Edge object for multi-graph will be different.
 * This object maintains a set of edges.
 * So edge object needs to implement the appropriate semantics of equals.
 * Created by avachat on 8/19/15.
 */
public class Vertex<IdType extends Comparable<IdType>> {

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

        Preconditions.checkNotNull(destination, "null param destination");
        Preconditions.checkNotNull(edge, "null param edge");

        Preconditions.checkArgument(edge.getSource().equals(this));
        Preconditions.checkArgument(edge.getDestination().equals(destination));

        Set<Edge<IdType>> setEdges = destinations.get(destination);
        if ( setEdges == null ) {
            setEdges = new HashSet<Edge<IdType>>();
            destinations.put(destination, setEdges);
        }

        Preconditions.checkArgument( ! setEdges.contains(edge), "Cannot add duplicate edge");
        setEdges.add(edge);

    }

    public void addSource(Vertex<IdType> source, Edge<IdType> edge) {

        Preconditions.checkNotNull(source, "null param destination");
        Preconditions.checkNotNull(edge, "null param edge");

        Preconditions.checkArgument(edge.getDestination().equals(this));
        Preconditions.checkArgument(edge.getSource().equals(source));

        Set<Edge<IdType>> setEdges = sources.get(source);
        if ( setEdges == null ) {
            setEdges = new HashSet<Edge<IdType>>();
            sources.put(source, setEdges);
        }

        Preconditions.checkArgument( ! setEdges.contains(edge), "Cannot add duplicate edge");
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
        return sources.keySet();
    }

    public Set<Vertex<IdType>> getDestinationVertices() {
        return destinations.keySet();
    }

    public IdType getId() {
        return id;
    }

    public Map<Vertex<IdType>, Set<Edge<IdType>>> getSources() {
        return sources;
    }

    public Map<Vertex<IdType>, Set<Edge<IdType>>> getDestinations() {
        return destinations;
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
}
