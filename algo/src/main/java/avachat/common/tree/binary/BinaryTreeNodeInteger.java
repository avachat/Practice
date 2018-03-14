package avachat.common.tree.binary;

import java.util.Optional;

/**
 * Created by Abhay Avachat on 3/7/18.
 */
public class BinaryTreeNodeInteger implements BinaryTreeNode {

  private int num;
  private BinaryTreeNodeInteger leftNode, rightNode;

  public BinaryTreeNodeInteger(int num) {
    this.num = num;
  }

  public void setLeftNode(BinaryTreeNodeInteger node) {
    if ( null == node) {
      throw new IllegalArgumentException("Left node cannot be null for " + num);
    }
    if (this == node) {
      throw new IllegalArgumentException("Self reference not allowed at " + num);
    }
  }

  @Override
  public Object getDataAtNode() {
    return null;
  }

  @Override
  public Optional<BinaryTreeNodeGeneric<T>> getLeftNode() {
    return null;
  }

  @Override
  public BinaryTreeNode getRightNode() {
    return null;
  }
}
