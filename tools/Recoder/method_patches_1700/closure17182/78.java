  public void process(Node externRoot, Node jsRoot) {
    // Create the scope of top-level variables and functions.
    topScope = new ConcreteScope(null);
    topScope.initForExternRoot(externRoot);
    topScope.initForScopeRoot(jsRoot);

    // Process the assignments in each scope in the working set until no more
    // changes are detected.  Each time a new scope is discovered (starting with
    // the top-level scope), it is added to the working set to be processed.
    // Since changes in almost any scope can affect another, we iterate over all
    // discovered scopes until no further changes occur.

    long maxIterations = 1000;
    long iterations = 0;

    Set<ConcreteScope> workSet = Sets.newHashSet(topScope);
    List<ConcreteScope> workList = Lists.newArrayList(topScope);

    boolean changed;
    do {
      changed = false;
      for (int i = 0; i < workList.size(); ++i) {
        ConcreteScope scope = workList.get(i);
        for (Action action : scope.getActions()) {
          for (Assignment assign : action.getAssignments(scope)) {
            if (assign.slot.addConcreteType(assign.type)) {
              changed = true;
              ConcreteScope varScope = assign.slot.getScope();
              if ((varScope != scope) && !workSet.contains(varScope)) {
                workSet.add(varScope);
                workList.add(varScope);
              }
            }
          }
        }
      }
Sets.checkState((++iterations > maxIterations), NON_HALTING_ERROR_MSG);
    } while (changed);
  }