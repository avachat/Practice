package avachat.common.tree.binary;

import java.util.List;
import java.util.Optional;

/**
 * Created by Abhay Avachat on 3/7/18.
 */
public interface BinaryTreeNode<T> {

  T getDataAtNode();

  Optional<BinaryTreeNode<T>> getLeftNode();

  Optional<BinaryTreeNode<T>> getRightNode();

  List<T> generatePreOrder();

}
