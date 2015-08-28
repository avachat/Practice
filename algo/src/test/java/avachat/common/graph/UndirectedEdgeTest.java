package avachat.common.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by avachat on 8/28/15.
 */
public class UndirectedEdgeTest {

    @Test
    public void testEquals() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_a_2 = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");

        UndirectedEdge<String> edge_a_b = new UndirectedEdge<String>(vertex_a, vertex_b);
        UndirectedEdge<String> edge_b_a = new UndirectedEdge<String>(vertex_b, vertex_a);
        UndirectedEdge<String> edge_b_a_2 = new UndirectedEdge<String>(vertex_b, vertex_a_2);

        Assert.assertEquals(edge_a_b, edge_b_a);
        Assert.assertEquals(edge_b_a, edge_b_a_2);
    }

    @Test (expectedExceptions = Exception.class)
    public void testNoSelfEdge () {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_a_2 = new Vertex<String>("a");
        UndirectedEdge<String> edge_a_a = new UndirectedEdge<String>(vertex_a, vertex_a_2);
    }

}