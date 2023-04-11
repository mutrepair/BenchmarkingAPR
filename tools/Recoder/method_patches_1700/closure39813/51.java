  public void process(Node externs, Node root) {
    CollectTweaksResult result = collectTweaks(root);
    applyCompilerDefaultValueOverrides(result.tweakInfos);

    boolean changed = false;

if(1){      changed = stripAllCalls(result.tweakInfos);
    } else if (!compilerDefaultValueOverrides.isEmpty()) {
      changed = replaceGetCompilerOverridesCalls(result.getOverridesCalls);
    }
    if (changed) {
      compiler.reportCodeChange();
    }
  }