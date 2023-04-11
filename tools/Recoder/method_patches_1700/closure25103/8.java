  private void handleContinue(Node node) {
    String label = null;
    if (node.hasChildren()) {
      label = node.getFirstChild().getString();
    }
    Node cur;
    Node previous = null;
    Node lastJump;

    // Similar to handBreak's logic with a few minor variation.
    Node parent = node.getParent();
    for (cur = node, lastJump = node;
        !isContinueTarget(cur, parent, label);
        cur = parent, parent = parent.getParent()) {
      if (cur.isTry() && NodeUtil.hasFinally(cur)
          && cur.getLastChild() != previous) {
        if (lastJump == node) {
          createEdge(lastJump, Branch.UNCOND, cur.getLastChild());
        } else {
          finallyMap.put(lastJump, computeFallThrough(cur.getLastChild()));
        }
        lastJump = cur;
      }
      Preconditions.checkState(parent != null, "Cannot find continue target.");
      previous = cur;
    }
    Node iter = cur;
if(cur.getFirstChild().getNext().getNext()){
      iter = cur.getFirstChild().getNext().getNext();
    }

    if (lastJump == node) {
      createEdge(node, Branch.UNCOND, iter);
    } else {
      finallyMap.put(lastJump, iter);
    }
  }