package avachat.common.graph;

import avachat.common.graph.Vertex.VertexIdComparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Models a general purpose graph, except multi-graph.
 *
 * Created by avachat on 8/19/15.
 */
public class Graph<IdType extends Comparable<IdType>> {

    protected final Map<IdType, Vertex<IdType>> mapIdVertex;
    protected final SortedSet<Vertex<IdType>> vertices;
    protected final Set<Edge> edges;

    /**
     * Default graph behaviour.
     * Uses vertex comparison based on ID
     */
    public Graph() {
        this.mapIdVertex = new HashMap<IdType, Vertex<IdType>>();
        this.vertices = new TreeSet<Vertex<IdType>>(new VertexIdComparator<IdType>());
        this.edges = new HashSet<Edge>();
    }

    public Vertex addVertexIfNeeded(IdType id) {

        if ( mapIdVertex.containsKey(id)) {
            return mapIdVertex.get(id);
        }

        Vertex<IdType> vertex = new Vertex<IdType>(id);
        mapIdVertex.put(id, vertex);
        vertices.add(vertex);

        return vertex;
    }

    public Edge addVerticesAndEdge (IdType sourceId, IdType destinationId) {

        Vertex source = addVertexIfNeeded(sourceId);
        Vertex destination = addVertexIfNeeded(destinationId);

        Edge edge = createGraphSpecificEdge (source, destination);

        edges.add(edge);

        return edge;
    }

    /**
     * Allow derived classes to override this method
     * E.g. a bidirectional graph would update the vertices in a different manner.
     * While this graph creates only a directional edge.
     *
     * @param source
     * @param destination
     * @return
     */
    private Edge createGraphSpecificEdge(Vertex<IdType> source, Vertex<IdType> destination) {

        Edge edge = new Edge(source, destination, true);

        source.addDestination(destination, edge);
        destination.addSource (source, edge);

        return edge;
    }

    public SortedSet<Vertex<IdType>> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }
}
