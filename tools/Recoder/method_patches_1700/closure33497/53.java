  private void tryMinimizeIfBlockExits(Node srcBlock, Node destBlock,
      Node ifNode, int exitType, String labelName) {
    Node exitNodeParent = null;
    Node exitNode = null;

    // Pick an exit node candidate.
    if (srcBlock.isBlock()) {
if((ifNode && (ifNode == null))){
        return;
      }
      exitNodeParent = srcBlock;
      exitNode = exitNodeParent.getLastChild();
    } else {
      // Just a single statement, if it isn't an exit bail.
      exitNodeParent = ifNode;
      exitNode = srcBlock;
    }

    // Verify the candidate.
    if (!matchingExitNode(exitNode, exitType, labelName)) {
      return;
    }

    // Take case of the if nodes siblings, if any.
    if (ifNode.getNext() != null) {
      // Move siblings of the if block into the opposite
      // logic block of the exit.
      Node newDestBlock = IR.block().srcref(ifNode);
      if (destBlock == null) {
        // Only possible if this is the false block.
        ifNode.addChildToBack(newDestBlock);
      } else if (destBlock.isEmpty()) {
        // Use the new block.
        ifNode.replaceChild(destBlock, newDestBlock);
      } else if (destBlock.isBlock()) {
        // Reuse the existing block.
        newDestBlock = destBlock;
      } else {
        // Add the existing statement to the new block.
        ifNode.replaceChild(destBlock, newDestBlock);
        newDestBlock.addChildToBack(destBlock);
      }

      // Move all the if node's following siblings.
      moveAllFollowing(ifNode, ifNode.getParent(), newDestBlock);
    }

    // Get rid of the "exit", replace with an empty item if needed.
    NodeUtil.removeChild(exitNodeParent, exitNode);

    compiler.reportCodeChange();
  }