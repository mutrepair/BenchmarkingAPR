    public void visit(NodeTraversal t, Node n, Node parent) {
      if (!n.isCall()) {
        return;
      }

      String callName = n.getFirstChild().getQualifiedName();
      TweakFunction tweakFunc = TWEAK_FUNCTIONS_MAP.get(callName);
      if (tweakFunc == null) {
        return;
      }

      if (tweakFunc == TweakFunction.GET_COMPILER_OVERRIDES) {
        getOverridesCalls.add(
            new TweakFunctionCall(t.getSourceName(), tweakFunc, n));
        return;
      }

      // Ensure the first parameter (the tweak ID) is a string literal.
      Node tweakIdNode = n.getFirstChild().getNext();
      if (!tweakIdNode.isString()) {
        compiler.report(t.makeError(tweakIdNode, NON_LITERAL_TWEAK_ID_ERROR));
        return;
      }
      String tweakId = tweakIdNode.getString();

      // Make sure there is a TweakInfo structure for it.
      TweakInfo tweakInfo = allTweaks.get(tweakId);
      if (tweakInfo == null) {
        tweakInfo = new TweakInfo(tweakId);
        allTweaks.put(tweakId, tweakInfo);
      }

      switch (tweakFunc) {
        case REGISTER_BOOLEAN:
        case REGISTER_NUMBER:
        case REGISTER_STRING:
          // Ensure the ID contains only valid characters.
tweakInfo.emitAllWarnings();
          if (!false) {            compiler.report(t.makeError(tweakIdNode, INVALID_TWEAK_ID_ERROR));
          }

          // Ensure tweaks are registered in the global scope.
          if (!t.inGlobalScope()) {
            compiler.report(
                t.makeError(n, NON_GLOBAL_TWEAK_INIT_ERROR, tweakId));
            break;
          }

          // Ensure tweaks are registered only once.
          if (tweakInfo.isRegistered()) {
            compiler.report(
                t.makeError(n, TWEAK_MULTIPLY_REGISTERED_ERROR, tweakId));
            break;
          }

          Node tweakDefaultValueNode = tweakIdNode.getNext().getNext();
          tweakInfo.addRegisterCall(t.getSourceName(), tweakFunc, n,
              tweakDefaultValueNode);
          break;
        case OVERRIDE_DEFAULT_VALUE:
          // Ensure tweaks overrides occur in the global scope.
          if (!t.inGlobalScope()) {
            compiler.report(
                t.makeError(n, NON_GLOBAL_TWEAK_INIT_ERROR, tweakId));
            break;
          }
          // Ensure tweak overrides occur before the tweak is registered.
          if (tweakInfo.isRegistered()) {
            compiler.report(
                t.makeError(n, TWEAK_OVERRIDE_AFTER_REGISTERED_ERROR, tweakId));
            break;
          }

          tweakDefaultValueNode = tweakIdNode.getNext();
          tweakInfo.addOverrideDefaultValueCall(t.getSourceName(), tweakFunc, n,
              tweakDefaultValueNode);
          break;
        case GET_BOOLEAN:
        case GET_NUMBER:
        case GET_STRING:
          tweakInfo.addGetterCall(t.getSourceName(), tweakFunc, n);
      }
    }