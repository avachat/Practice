package avachat.common.graph;

import avachat.common.graph.Vertex.VertexIdComparator;
import com.google.common.base.Preconditions;
import java.util.Objects;

/**
 * Class to model undirected graph.
 * Created by avachat on 8/25/15.
 */
public class UndirectedEdge<T extends Comparable<T>> extends Edge<T> {

    //
    // Just diff names for source and destinations based on their natural sorting order
    protected final Vertex<T> lowerVertex, upperVertex;

    /**
     * Creates an undirectional edge
     *
     * @param source source
     * @param destination destination
     * @param forwardWeight forward weight
     * @param backwardWeight backward weight
     */
    public UndirectedEdge(Vertex<T> source, Vertex<T> destination, double forwardWeight, double backwardWeight) {

        super(source, destination, false, forwardWeight, backwardWeight);

        // Self edges are not allowed
        Preconditions.checkArgument( !(source.equals(destination)), "Source and vertex cannot be same");

        VertexIdComparator<T> vertexIdComparator = new VertexIdComparator<>();

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
     * @param source source
     * @param destination destination
     */
    public UndirectedEdge(Vertex<T> source, Vertex<T> destination) {
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

        @SuppressWarnings("unchecked")
        UndirectedEdge<T> other = (UndirectedEdge<T>) obj;

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
