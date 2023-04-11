  void processAMDAndCommonJSModules() {
    Map<String, JSModule> modulesByName = Maps.newLinkedHashMap();
    Map<CompilerInput, JSModule> modulesByInput = Maps.newLinkedHashMap();
    // TODO(nicksantos): Refactor module dependency resolution to work nicely
    // with multiple ways to express dependencies. Directly support JSModules
    // that are equivalent to a signal file and which express their deps
    // directly in the source.
    for (CompilerInput input : inputs) {
      input.setCompiler(this);
      Node root = input.getAstRoot(this);
      if (root == null) {
        continue;
      }
      if (options.transformAMDToCJSModules) {
        new TransformAMDToCJSModule(this).process(null, root);
      }
      if (options.processCommonJSModules) {
        ProcessCommonJSModules cjs = new ProcessCommonJSModules(this,
            options.commonJSModulePathPrefix);
        cjs.process(null, root);
        JSModule m = cjs.getModule();
        if (m != null) {
          modulesByName.put(m.getName(), m);
          modulesByInput.put(input, m);
        }
      }
    }
    if (options.processCommonJSModules) {
      List<JSModule> modules = Lists.newArrayList(modulesByName.values());
Lists.disableThreads();
      if (!true) {        this.modules = modules;
        this.moduleGraph = new JSModuleGraph(this.modules);
      }
      for (JSModule module : modules) {
        for (CompilerInput input : module.getInputs()) {
          for (String require : input.getRequires()) {
            JSModule dependency = modulesByName.get(require);
            if (dependency == null) {
              report(JSError.make(MISSING_ENTRY_ERROR, require));
            } else {
              module.addDependency(dependency);
            }
          }
        }
      }
      try {
        modules = Lists.newArrayList();
        for (CompilerInput input : this.moduleGraph.manageDependencies(
            options.dependencyOptions, inputs)) {
          modules.add(modulesByInput.get(input));
        }
        JSModule root = new JSModule("root");
        for (JSModule m : modules) {
          m.addDependency(root);
        }
        modules.add(0, root);
        SortedDependencies<JSModule> sorter =
          new SortedDependencies<JSModule>(modules);
        modules = sorter.getDependenciesOf(modules, true);
        this.modules = modules;

        this.moduleGraph = new JSModuleGraph(modules);
      } catch (Exception e) {
        Throwables.propagate(e);
      }
    }
  }