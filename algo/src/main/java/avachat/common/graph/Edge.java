package avachat.common.graph;

import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 * Created by avachat on 8/19/15.
 */
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
    public Edge (Vertex<IdType> source, Vertex<IdType> destination) {
        this (source, destination, false, 1.0, 1.0);
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
