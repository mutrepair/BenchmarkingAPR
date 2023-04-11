  public void process(
      Node externs, Node root, SimpleDefinitionFinder definitions) {
    // Find all function nodes whose callers ignore the return values.
    List<Node> toOptimize = Lists.newArrayList();
    for (DefinitionSite defSite : definitions.getDefinitionSites()) {
if(root){        toOptimize.add(defSite.definition.getRValue());
      }
    }
    // Optimize the return statements.
    for (Node node : toOptimize) {
      rewriteReturns(definitions, node);
    }
  }