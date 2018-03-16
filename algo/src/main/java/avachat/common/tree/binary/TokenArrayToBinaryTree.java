package avachat.common.tree.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Abhay Avachat on 3/8/18.
 */
public class TokenArrayToBinaryTree<T> implements Function<List<String>, BinaryTreeNode<T>> {

  private final Function<String, T> converter;

  public TokenArrayToBinaryTree (Function<String, T> converter) {
    this.converter = converter;
  }

  public BinaryTreeNode<T> apply(List<String> tokenList) {
    if ( tokenList.isEmpty()) {
      throw new IllegalArgumentException("Must contain at leat 1 element");
    }

    String dataAtRootNode = tokenList.get(0);
    return processTokenList(dataAtRootNode, tokenList.subList(1, tokenList.size()));
  }

  private BinaryTreeNode<T> processTokenList (String dataAtRootNode, List<String> tokenList) {

    Deque<BinaryTreeNodeGeneric<T>> stack = new ArrayDeque<>(tokenList.size() + 1);

    BinaryTreeNodeGeneric<T> rootNode = new BinaryTreeNodeGeneric<>(converter.apply(dataAtRootNode));
    stack.addFirst(rootNode);

    for (String token : tokenList) {
      if ( "(".equals(token)) {
        beginNode(stack);
      } else if (")".equals(token)){
        endNode(stack);
      } else {
        processData(stack, token);
      }
    }

    return rootNode;
  }

  private void beginNode(Deque<BinaryTreeNodeGeneric<T>> stack) {
    // empty for the time being
  }

  private void processData(Deque<BinaryTreeNodeGeneric<T>> stack, String token) {
    T dataAtNode = converter.apply(token);
    BinaryTreeNodeGeneric<T> node = new BinaryTreeNodeGeneric<>(dataAtNode);
    stack.addFirst(node);
  }

  private void endNode(Deque<BinaryTreeNodeGeneric<T>> stack) {

    // pop current node
    BinaryTreeNodeGeneric<T> node = stack.removeFirst();
    if ( null == node) {
      throw new IllegalArgumentException("No node found after end marker");
    }

    // access the parent node
    BinaryTreeNodeGeneric<T> parentNode = stack.peekFirst();
    if ( null == parentNode) {
      throw new IllegalArgumentException("No parent found after end node");
    }

    if (!parentNode.getLeftNode().isPresent()) {
      parentNode.setLeftNode(node);
    } else if (!parentNode.getRightNode().isPresent()) {
      parentNode.setRightNode(node);
    } else {
      throw new IllegalArgumentException("Incorrectly formatted input node data = " + node.getDataAtNode()
          + " parent = " + parentNode.getDataAtNode());
    }
  }

}
