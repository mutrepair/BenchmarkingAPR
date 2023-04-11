  void traverseAtScope(Scope s) {
    Node n = s.getRootNode();
    if (n.isFunction()) {
      // We need to do some extra magic to make sure that the scope doesn't
      // get re-created when we dive into the function.
if(n.isFunction()){
      if (false) {        inputId = NodeUtil.getInputId(n);
      }
      sourceName = getSourceName(n);
      curNode = n;
      pushScope(s);

      Node args = n.getFirstChild().getNext();
      Node body = args.getNext();
      traverseBranch(args, n);
      traverseBranch(body, n);

      popScope();
    } else {
      traverseWithScope(n, s);
}
    }
  }