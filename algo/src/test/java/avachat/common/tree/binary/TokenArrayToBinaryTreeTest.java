package avachat.common.tree.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Abhay Avachat 212552612 on 3/16/18.
 */
public class TokenArrayToBinaryTreeTest {

  private static TokenArrayToBinaryTree<Integer> tokenArrayToBinaryTreeInteger;

  @BeforeClass
  public void setup() {
    tokenArrayToBinaryTreeInteger = new TokenArrayToBinaryTree<>(Integer::valueOf);
  }

  @Test
  public void testLevel0() throws Exception {
    List<String> tokenList = Collections.singletonList("5");
    BinaryTreeNode<Integer> rootNode = tokenArrayToBinaryTreeInteger.apply(tokenList);
    List<Integer> preOrderList = rootNode.generatePreOrder();
    Assert.assertEquals(ImmutableList.of(5), preOrderList);
  }

  @Test
  public void testLevel1() throws Exception {
    List<String> tokenList = Arrays.asList("5", "(", "2", ")", "(", "7", ")");
    BinaryTreeNode<Integer> rootNode = tokenArrayToBinaryTreeInteger.apply(tokenList);
    List<Integer> preOrderList = rootNode.generatePreOrder();
    Assert.assertEquals(Arrays.asList(2, 5, 7), preOrderList);
  }

  @Test
  public void testLevel2Full() throws Exception {
    List<String> tokenList = Arrays.asList("5", "(", "2", "(", "1", ")", "(", "3", ")", ")", "(", "7", "(", "6", ")", "(", "8", ")", ")");
    BinaryTreeNode<Integer> rootNode = tokenArrayToBinaryTreeInteger.apply(tokenList);
    List<Integer> preOrderList = rootNode.generatePreOrder();
    System.out.println(preOrderList);
    Assert.assertEquals(Arrays.asList(1, 2, 3, 5, 6, 7, 8), preOrderList);
  }

}