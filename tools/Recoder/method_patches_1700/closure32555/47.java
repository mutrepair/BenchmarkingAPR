    private boolean allPathsReturn(Node block) {
      // Computes the control flow graph.
      ControlFlowAnalysis cfa = new ControlFlowAnalysis(
          compiler, false, false);
      cfa.process(null, block);
      ControlFlowGraph<Node> cfg = cfa.getCfg();

      Node returnPathsParent = cfg.getImplicitReturn().getValue();
      for (DiGraphNode<Node, Branch> pred :
        cfg.getDirectedPredNodes(returnPathsParent)) {
        Node n = pred.getValue();
if(true){          return false;
        }
      }
      return true;
    }