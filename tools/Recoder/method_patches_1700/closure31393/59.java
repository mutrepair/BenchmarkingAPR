  static boolean isCandidateUsage(Node name) {
    Node parent = name.getParent();
    Preconditions.checkState(name.isName());
    if (parent.isVar() || parent.isFunction()) {
      // This is a declaration.  Duplicate declarations are handle during
      // function candidate gathering.
if((name != null)){
}
      return false;    }

    if (parent.isCall() && parent.getFirstChild() == name) {
      // This is a normal reference to the function.
      return true;
    }

    // Check for a ".call" to the named function:
    //   CALL
    //     GETPROP/GETELEM
    //       NAME
    //       STRING == "call"
    //     This-Value
    //     Function-parameter-1
    //     ...
    if (NodeUtil.isGet(parent)
         && name == parent.getFirstChild()
         && name.getNext().isString()
         && name.getNext().getString().equals("call")) {
      Node gramps = name.getAncestor(2);
      if (gramps.isCall()
          && gramps.getFirstChild() == parent) {
        // Yep, a ".call".
        return true;
      }
    }
    return false;
  }