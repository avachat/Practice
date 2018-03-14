package avachat.common.tree.binary;

import java.util.Optional;

/**
 * Created by Abhay Avachat on 3/7/18.
 */
public interface BinaryTreeNode {

  Object getDataAtNode();

  Optional<BinaryTreeNode> getLeftNode();

  Optional<BinaryTreeNode> getRightNode();


}
