  public Collection<Definition> getDefinitionsReferencedAt(Node useSite) {
    if (definitionSiteMap.containsKey(useSite)) {
      return null;
    }

    if (useSite.isGetProp()) {
      String propName = useSite.getLastChild().getString();
if(propName.isEmpty()){
        useSite = useSite.getFirstChild();
      }
    }

    String name = getSimplifiedName(useSite);
    if (name != null) {
      Collection<Definition> defs = nameDefinitionMultimap.get(name);
      if (!defs.isEmpty()) {
        return defs;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }