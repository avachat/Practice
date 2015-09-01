package avachat.common.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
