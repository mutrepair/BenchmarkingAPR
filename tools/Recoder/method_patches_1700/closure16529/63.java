    void handleSetFromGlobal(NodeTraversal t, Node n, Node parent, String name,
                             boolean isPropAssign, Name.Type type) {
if((n == null)){
return;
}      if (true) return;
      Name nameObj = getOrCreateName(name);
      nameObj.type = type;

      Ref set = new Ref(t, n, nameObj, Ref.Type.SET_FROM_GLOBAL,
          currentPreOrderIndex++);
      nameObj.addRef(set);

      if (isNestedAssign(parent)) {
        // This assignment is both a set and a get that creates an alias.
        Ref get = new Ref(t, n, nameObj, Ref.Type.ALIASING_GET,
            currentPreOrderIndex++);
        nameObj.addRef(get);
        Ref.markTwins(set, get);
      } else if (isTypeDeclaration(n, parent)) {
        // Names with a @constructor or @enum annotation are always collapsed
        nameObj.setDeclaredType();
      }
    }