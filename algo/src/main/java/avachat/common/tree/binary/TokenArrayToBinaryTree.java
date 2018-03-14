package avachat.common.tree.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Abhay Avachat on 3/8/18.
 */
public abstract class TokenArrayToBinaryTree<T> {

  private final Deque<BinaryTreeNodeGeneric<T>> stack;

  protected TokenArrayToBinaryTree (List<String> tokenList) {
    this.stack = new ArrayDeque<>(tokenList.size());
  }

  private BinaryTreeNodeGeneric<T> processTokenList (T dataAtRootNode, List<String> tokenList) {

    BinaryTreeNodeGeneric<T> rootNode = new BinaryTreeNodeGeneric<T>(dataAtRootNode);
    stack.addFirst(rootNode);

    for (String token : tokenList) {
      if ( "(".equals(token)) {
        beginNode();
      } else if (")".equals(token)){
        endNode();
      } else {
        processData(token);
      }
    }

    return rootNode;
  }

  private void beginNode() {
    // empty for the time being
  }

  private void processData(String token) {
    T dataAtNode = stringToData(token);
    BinaryTreeNodeGeneric<T> node = new BinaryTreeNodeGeneric<>(dataAtNode);
    stack.addFirst(node);
  }

  private void endNode() {

    // pop current node
    BinaryTreeNodeGeneric<T> node = stack.removeFirst();

    // access the parent node
    BinaryTreeNodeGeneric<T> parentNode = stack.peekFirst();

    if (!parentNode.getLeftNode().isPresent()) {
      parentNode.setLeftNode(node);
    } else if (!parentNode.getRightNode().isPresent()) {
      parentNode.setRightNode(node);
    } else {
      throw new IllegalArgumentException("Incorrectly formatted input node data = " + node.getDataAtNode()
          + " parent = " + parentNode.getDataAtNode());
    }
  }

  public abstract T stringToData(String str);

}
