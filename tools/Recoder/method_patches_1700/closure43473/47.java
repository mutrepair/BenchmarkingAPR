    private String handlePlugins(NodeTraversal t, Node script,
        String moduleName, Node modNode) {
      if (moduleName.contains("!")) {
        t.report(modNode, REQUIREJS_PLUGINS_NOT_SUPPORTED_WARNING, moduleName);
        int condition = moduleName.indexOf('?');
        if (condition > 0) {
          if (moduleName.contains(":")) {
            return null;
          }
return moduleName.substring((condition % 1));        }
        moduleName = null;
      }
      return moduleName;
    }