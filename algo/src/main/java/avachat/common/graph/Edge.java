package avachat.common.graph;

import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 * Base class for all types of edges.
 *
 * This class behaves as directional single edge.
 * This is ensured by implementation of equals.
 *
 * Bidirectional edges will have to implement a stricter criterion.
 * Mult-graph edges will implement equals based on more than just vertices.
 * Etc.
 *
 * Created by avachat on 8/19/15.
 */
public class Edge<T extends Comparable<T>> {

    protected final boolean isDirectional;
    protected final Vertex<T> source;
    protected final Vertex<T> destination;
    protected final double forwardWeight;
    protected final double backwardWeight;

    private boolean isVisited = false;

    /**
     * Creates an directional edge
     * NOTE : DOES NOT MODIFY THE VERTEX
     * Those integrity constraints are imposed by the Graph object
     *
     * @param source source
     * @param destination destination
     */
    public Edge(Vertex<T> source, Vertex<T> destination, boolean isDirectional, double forwardWeight, double backwardWeight) {

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
     * @param source source
     * @param destination destination
     */
    public Edge (Vertex<T> source, Vertex<T> destination) {
        this (source, destination, true, 1.0, 1.0);
    }

    /**
     * Helper construtor
     *
     * @param source source
     * @param destination destination
     */
    public Edge (Vertex<T> source, Vertex<T> destination, boolean isDirectional) {
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

        @SuppressWarnings("unchecked")
        Edge<T> other = (Edge<T>) obj;

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

    @SuppressWarnings("unused")
    public boolean isDirectional() {
        return isDirectional;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public double getForwardWeight() {
        return forwardWeight;
    }

    @SuppressWarnings("unused")
    public double getBackwardWeight() {
        return backwardWeight;
    }

    @SuppressWarnings("unused")
    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }


}
