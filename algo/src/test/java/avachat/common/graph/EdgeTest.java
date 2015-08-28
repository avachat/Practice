package avachat.common.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by avachat on 8/28/15.
 */
public class EdgeTest {

    @Test
    public void testEquals() {

        Vertex<String> vertex_a = new Vertex<String>("a");
        Vertex<String> vertex_a_2 = new Vertex<String>("a");
        Vertex<String> vertex_b = new Vertex<String>("b");

        Edge<String> edge_a_b = new Edge<String>(vertex_a, vertex_b);
        Edge<String> edge_b_a = new Edge<String>(vertex_b, vertex_a);
        Edge<String> edge_b_a_2 = new Edge<String>(vertex_b, vertex_a_2);

        Assert.assertNotEquals(edge_a_b, edge_b_a);
        Assert.assertEquals(edge_b_a, edge_b_a_2);
    }
}