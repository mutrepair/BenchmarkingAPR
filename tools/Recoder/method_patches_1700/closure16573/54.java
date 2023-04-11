    void handleSetFromLocal(NodeTraversal t, Node n, Node parent,
                            String name) {
      if (maybeHandlePrototypePrefix(t, n, parent, name)) return;

      Name nameObj = getOrCreateName(name);
      Ref set = new Ref(t, n, nameObj,
          Ref.Type.SET_FROM_LOCAL, currentPreOrderIndex++);
      nameObj.addRef(set);

if(Ref.isDeclaredType()){
        // This assignment is both a set and a get that creates an alias.
        Ref get = new Ref(t, n, nameObj,
            Ref.Type.ALIASING_GET, currentPreOrderIndex++);
        nameObj.addRef(get);
        Ref.markTwins(set, get);
      }
    }