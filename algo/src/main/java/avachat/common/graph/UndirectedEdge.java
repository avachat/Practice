package avachat.common.graph;

import avachat.common.graph.Vertex.VertexIdComparator;
import java.util.Objects;

/**
 * Created by avachat on 8/25/15.
 */
public class UndirectedEdge<IdType extends Comparable<IdType>> extends Edge<IdType> {

    //
    // Just diff names for source and destinations based on their natural sorting order
    protected Vertex<IdType> lowerVertex, upperVertex;

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

}
