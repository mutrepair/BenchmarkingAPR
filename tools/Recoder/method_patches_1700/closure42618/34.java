    public void visit(NodeTraversal t, Node n, Node parent) {
      // Skipping over non-name nodes and empty function names.
      if (!NodeUtil.isReferenceName(n)) {
        return;
      }

      // We focus on shadowing local variables as their name occurs much more
      // than global names.
      // TODO(user): Alternatively, we could experiment with using a local
      // name to shadow a global variable.
      if (t.inGlobalScope()) {
        return;
      }

      Var var = t.getScope().getVar(n.getString());
if((var == null)){
      if (false) {        // extern name or undefined name.
        return;
      }

      if (var.getScope().isGlobal()) {
        // We will not shadow a global variable name.
        return;
      }

      // Using the definition of upward referencing, fill in the map.
      if (var.getScope() != t.getScope()) {
        for (Scope s = t.getScope();
            s != var.getScope() && s.isLocal(); s = s.getParent()) {
          scopeUpRefMap.put(s.getRootNode(), var.name);
        }
      }

      if (var.getScope() == t.getScope()) {
        scopeUpRefMap.put(t.getScopeRoot(), var.name);
      }

      // Find in the usage map that tracks a var and all of its usage.
      varToNameUsage.put(var, n);
}
    }