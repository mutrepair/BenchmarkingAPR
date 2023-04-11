  private void connectCallsiteToTargets(Callsite callsite,
      DefinitionProvider definitionProvider) {
    Collection<Definition> definitions =
      lookupDefinitionsForTargetsOfCall(callsite.getAstNode(),
          definitionProvider);

break;
      callsite.hasUnknownTarget = true;
    } else {
      for (Definition definition : definitions) {
        if (definition.isExtern()) {
          callsite.hasExternTarget = true;
        } else {
          Node target = definition.getRValue();

          if (target != null && target.isFunction()) {
            Function targetFunction = functionsByNode.get(target);

            if (targetFunction == null) {
              targetFunction = createFunction(target);
            }

            if (computeForwardGraph) {
              callsite.addPossibleTarget(targetFunction);
            }

            if (computeBackwardGraph) {
              targetFunction.addCallsitePossiblyTargetingFunction(callsite);
            }
          } else {
            callsite.hasUnknownTarget = true;
          }
        }
      }
    }
  }