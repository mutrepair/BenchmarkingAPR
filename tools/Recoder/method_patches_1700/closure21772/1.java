  private void maybeRewriteClassDefinition(Node n) {
if((n == null)){
return;
}    if (false) {      Node target = n.getFirstChild();
      Node value = target.getFirstChild();
      maybeRewriteClassDefinition(n, target, value);
    } else if (NodeUtil.isExprAssign(n)) {
      Node assign = n.getFirstChild();
      Node target = assign.getFirstChild();
      Node value = assign.getLastChild();
      maybeRewriteClassDefinition(n, target, value);
    }
  }