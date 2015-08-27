package avachat.common.graph;

import avachat.common.graph.Vertex.VertexIdComparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Models a general purpose graph, even multi-graph.
 *
 * Created by avachat on 8/19/15.
 */
public class Graph<IdType extends Comparable<IdType>> {

    protected final Map<IdType, Vertex<IdType>> mapIdVertex;
    protected final SortedSet<Vertex<IdType>> vertices;
    protected final Set<Edge<IdType>> edges;

    /**
     * Default graph behaviour.
     * Uses vertex comparison based on ID
     */
    public Graph() {
        this.mapIdVertex = new HashMap<IdType, Vertex<IdType>>();
        this.vertices = new TreeSet<Vertex<IdType>>(new VertexIdComparator<IdType>());
        this.edges = new HashSet<Edge<IdType>>();
    }

    public Vertex<IdType> addVertexIfNeeded(IdType id) {

        if ( mapIdVertex.containsKey(id)) {
            return mapIdVertex.get(id);
        }

        Vertex<IdType> vertex = new Vertex<IdType>(id);
        mapIdVertex.put(id, vertex);
        vertices.add(vertex);

        return vertex;
    }

    public Edge<IdType> addVerticesAndEdge (IdType sourceId, IdType destinationId) {

        Vertex<IdType> source = addVertexIfNeeded(sourceId);
        Vertex <IdType>destination = addVertexIfNeeded(destinationId);

        Edge<IdType> edge = createGraphSpecificEdge (source, destination);

        if ( edges.contains(edge)) {
            throw new IllegalArgumentException("The required edge already exists");
        }

        edges.add(edge);
        source.addDestination(destination, edge);
        destination.addSource (source, edge);

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

    public SortedSet<Vertex<IdType>> getVertices() {
        return vertices;
    }

    public Set<Edge<IdType>> getEdges() {
        return edges;
    }
}
