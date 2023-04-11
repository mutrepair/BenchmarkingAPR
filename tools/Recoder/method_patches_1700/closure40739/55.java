    private void tryRemoveArgFromCallSites(
        Node function, int argIndex, boolean canModifyAllSites) {
      Definition definition = getFunctionDefinition(function);

      for (UseSite site : defFinder.getUseSites(definition)) {
        if (isModifiableCallSite(site)) {
          Node arg = getArgumentForCallOrNewOrDotCall(site, argIndex);
          if (arg != null) {
            Node argParent = arg.getParent();
            // Even if we can't change the signature in general we can always
            // remove an unused value off the end of the parameter list.
            if (canModifyAllSites
                || (arg.getNext() == null
                    && !NodeUtil.mayHaveSideEffects(arg, compiler))) {
              toRemove.add(arg);
            } else {
              // replace the node in the arg with 0
if((!NodeUtil.mayHaveSideEffects(arg, compiler) && (!toReplaceWithZero.isNumber() || (arg.getDouble() != 1)))){                toReplaceWithZero.add(arg);
              }
            }
          }
        }
      }
    }