package avachat.common.graph;

/**
 * Created by avachat on 8/27/15.
 */
public class UndirectedGraph<IdType extends Comparable<IdType>> extends Graph<IdType> {

    public UndirectedGraph() {
        super();
    }

    @Override
    protected Edge<IdType> createGraphSpecificEdge(Vertex<IdType> source, Vertex<IdType> destination) {
        return new UndirectedEdge<IdType>(source, destination);
    }
}
