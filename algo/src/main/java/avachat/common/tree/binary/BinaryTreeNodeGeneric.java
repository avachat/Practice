package avachat.common.tree.binary;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Abhay Avachat on 3/7/18.
 */
public class BinaryTreeNodeGeneric<T> implements BinaryTreeNode<T> {

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
  public T getDataAtNode() {
    return dataAtNode;
  }

  @Override
  public Optional<BinaryTreeNode<T>> getLeftNode() {
    return Optional.ofNullable(leftNode);
  }

  @Override
  public Optional<BinaryTreeNode<T>> getRightNode() {
    return Optional.ofNullable(rightNode);
  }

  @Override
  public List<T> generatePreOrder() {
    List<T> list = new ArrayList<>();
    generatePreOrderRecursive(list, this);
    return list;
  }

  private void generatePreOrderRecursive(List<T> list, BinaryTreeNodeGeneric<T> node) {

    if ( null != node.leftNode) {
      generatePreOrderRecursive(list, node.leftNode);
    }

    list.add(node.dataAtNode);

    if ( null != node.rightNode) {
      generatePreOrderRecursive(list, node.rightNode);
    }

  }

}
