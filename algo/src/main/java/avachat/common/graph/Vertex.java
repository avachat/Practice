package avachat.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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
public class Vertex<T extends Comparable<T>> {

    private final T id;

    /**
     * Edges.
     * NOTE : Data integrity is maintained by the graph object.
     */
    private final Map<Vertex<T>, Set<Edge<T>>> sources;
    private final Map<Vertex<T>, Set<Edge<T>>> destinations;

    /**
     * Following variables are useful to traversal algorithms
     */
    private boolean isVisited = false;
    private int hopCount = 0;
    private double cumulativeWight = 0.0;

    public Vertex (T id) {

        if ( (id == null) || (id.toString().isEmpty())) {
            throw new IllegalArgumentException("Vertex id cannot be null");
        }

        this.id = id;
        this.sources = new HashMap<>();
        this.destinations = new HashMap<>();

        this.isVisited = false;
        this.hopCount = 0;
        this.cumulativeWight = 0.0;
    }

    public void addDestination(Vertex<T> destination, Edge<T> edge) {

        Preconditions.checkNotNull(destination, "null param destination");
        Preconditions.checkNotNull(edge, "null param edge");

        Preconditions.checkArgument(edge.getSource().equals(this));
        Preconditions.checkArgument(edge.getDestination().equals(destination));

        Set<Edge<T>> setEdges = destinations.get(destination);
        if ( setEdges == null ) {
            setEdges = new HashSet<>();
            destinations.put(destination, setEdges);
        }

        Preconditions.checkArgument( ! setEdges.contains(edge), "Cannot add duplicate edge");
        setEdges.add(edge);

    }

    public void addSource(Vertex<T> source, Edge<T> edge) {

        Preconditions.checkNotNull(source, "null param destination");
        Preconditions.checkNotNull(edge, "null param edge");

        Preconditions.checkArgument(edge.getDestination().equals(this));
        Preconditions.checkArgument(edge.getSource().equals(source));

        Set<Edge<T>> setEdges = sources.get(source);
        if ( setEdges == null ) {
            setEdges = new HashSet<>();
            sources.put(source, setEdges);
        }

        Preconditions.checkArgument( ! setEdges.contains(edge), "Cannot add duplicate edge");
        setEdges.add(edge);

    }

    public boolean isSourceOf (Vertex<T> other) {
        return (destinations.containsKey(other));
    }

    public boolean isDestinationOf (Vertex<T> other) {
        return (sources.containsKey(other));
    }

    public boolean isConnectedTo (Vertex<T> other) {
        return ( isSourceOf(other) || isDestinationOf(other));
    }

    public Set<Edge<T>> getSourceEdges() {
        Set<Edge<T>> allEdges = new HashSet<>();
        sources.values().forEach((c) -> allEdges.addAll(c));
        return allEdges;
    }

    public Set<Edge<T>> getDestinationEdges() {
        Set<Edge<T>> allEdges = new HashSet<>();
        destinations.values().forEach((c) -> allEdges.addAll(c));
        return allEdges;
    }

    public Set<Vertex<T>> getSourceVertices() {
        return ImmutableSet.copyOf(sources.keySet());
    }

    public Set<Vertex<T>> getDestinationVertices() {
        return ImmutableSet.copyOf(destinations.keySet());
    }

    public T getId() {
        return id;
    }

    public Map<Vertex<T>, Set<Edge<T>>> getSources() {
        return ImmutableMap.copyOf(sources);
    }

    public Map<Vertex<T>, Set<Edge<T>>> getDestinations() {
        return ImmutableMap.copyOf(destinations);
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

    @SuppressWarnings("unused")
    public double getCumulativeWight() {
        return cumulativeWight;
    }

    public void setCumulativeWight(double cumulativeWight) {
        this.cumulativeWight = cumulativeWight;
    }

    @SuppressWarnings("unused")
    public int incrementHopCount() {
        return ++hopCount;
    }

    public double addToCumulativeWeight(double forwardWeight) {
        cumulativeWight += forwardWeight;
        return cumulativeWight;
    }

    /**
     * Helper
     * @return debug string
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

        @SuppressWarnings("unchecked")
        Vertex<T> other = (Vertex<T>) obj;

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
    public static class VertexIdComparator<I extends Comparable<I>> implements Comparator<Vertex<I>> {

        public int compare(Vertex<I> o1, Vertex<I> o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }
}
