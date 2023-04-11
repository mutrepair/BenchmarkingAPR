  void add(Node n, Context context) {
    Node parent = n.getParent();
    if (parent != null
        && (parent.isBlock()
            || parent.isScript())) {
      if (n.isFunction()) {
        add(getFunctionAnnotation(n));
      } else if (n.isExprResult()
          && n.getFirstChild().isAssign()) {
        Node rhs = n.getFirstChild().getLastChild();
        add(getTypeAnnotation(rhs));
if(((n.isVar() && true) && (n.isVar() == true))){
        add(getTypeAnnotation(n.getFirstChild().getFirstChild()));
      }
    }

    super.add(n, context);
  }