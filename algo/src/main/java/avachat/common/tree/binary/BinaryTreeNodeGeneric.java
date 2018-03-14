package avachat.common.tree.binary;


import java.util.Optional;

/**
 * Created by Abhay Avachat on 3/7/18.
 */
public class BinaryTreeNodeGeneric<T> implements BinaryTreeNode {

  private T dataAtNode;
  private BinaryTreeNodeGeneric<T> leftNode;
  private BinaryTreeNodeGeneric<T> rightNode;

  public BinaryTreeNodeGeneric(T dataAtNode) {
    if (null == dataAtNode) {
      throw new IllegalArgumentException("Null not allowed for data");
    }
    this.dataAtNode = dataAtNode;
  }

  public void setLeftNode(BinaryTreeNodeGeneric<T> node) {
    if ( null == node) {
      throw new IllegalArgumentException("Left node cannot be null for " + String.valueOf(dataAtNode));
    }
    if (this == node) {
      throw new IllegalArgumentException("Self reference not allowed at " + String.valueOf(dataAtNode));
    }

    this.leftNode = node;
  }

  public void setRightNode(BinaryTreeNodeGeneric<T> node) {
    if ( null == node) {
      throw new IllegalArgumentException("Right node cannot be null for " + String.valueOf(dataAtNode));
    }
    if (this == node) {
      throw new IllegalArgumentException("Self reference not allowed at " + String.valueOf(dataAtNode));
    }

    this.rightNode = node;
  }

  @Override
  public Object getDataAtNode() {
    return dataAtNode;
  }

  @Override
  public Optional<BinaryTreeNode> getLeftNode() {
    return Optional.ofNullable(leftNode);
  }

  @Override
  public Optional<BinaryTreeNode> getRightNode() {
    return Optional.ofNullable(rightNode);
  }
}
