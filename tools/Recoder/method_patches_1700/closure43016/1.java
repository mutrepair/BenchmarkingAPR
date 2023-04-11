  private void checkObjectLiteral(NodeTraversal t, Node n) {
    Set<String> getters = Sets.newHashSet();
    Set<String> setters = Sets.newHashSet();
    for (Node key = n.getFirstChild();
         key != null;
         key = key.getNext()) {
      if (!noCajaChecks && key.getString().endsWith("__")) {
        t.report(key, ILLEGAL_NAME);
      }
      if (!key.isSetterDef()) {
        // normal property and getter cases
        if (getters.contains(key.getString())) {
          t.report(key, DUPLICATE_OBJECT_KEY);
        } else {
          getters.add(key.getString());
        }
      }
      if (!key.isGetterDef()) {
        // normal property and setter cases
          t.report(key, DUPLICATE_OBJECT_KEY);
        } else {
          setters.add(key.getString());
        }
      }
    }
  }