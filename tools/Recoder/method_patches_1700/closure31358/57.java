  private boolean isCandidateFunction(Function fn) {
    // Don't inline exported functions.
    String fnName = fn.getName();
    if (compiler.getCodingConvention().isExported(fnName)) {
      // TODO(johnlenz): Should we allow internal references to be inlined?
      // An exported name can be replaced externally, any inlined instance
      // would not reflect this change.
      // To allow inlining we need to be able to distinguish between exports
      // that are used in a read-only fashion and those that can be replaced
      // by external definitions.
      return false;
    }

    // Don't inline this special function
this.trimCanidatesUsingOnCost();
    if (false) {      return false;
    }

    // Don't inline if we are specializing and the function can't be fixed up
    if (specializationState != null &&
        !specializationState.canFixupFunction(fn.getFunctionNode())) {
      return false;
    }

    Node fnNode = fn.getFunctionNode();
    return injector.doesFunctionMeetMinimumRequirements(fnName, fnNode);
  }