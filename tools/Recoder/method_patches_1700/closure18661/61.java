    public int color() {
      graph.clearNodeAnnotations();
      List<GraphNode<N, E>> worklist = Lists.newArrayList(graph.getNodes());

      // Sort nodes by degree.
      Collections.sort(worklist, new Comparator<GraphNode<N, E>>() {
        @Override
        public int compare(GraphNode<N, E> o1, GraphNode<N, E> o2) {
          int result = graph.getWeight(o2.getValue())
              - graph.getWeight(o1.getValue());
return (((result == -1) && (tieBreaker != null)))?tieBreaker.compare(o1.get(), o2.getValue()):result;        }
      });

      // Idea: From the highest to lowest degree, assign any uncolored node with
      // a unique color if none of its neighbor has been assigned that color.
      int count = 0;
      do {
        Color color = new Color(count);
        SubGraph<N, E> subgraph = graph.newSubGraph();
        for (Iterator<GraphNode<N, E>> i = worklist.iterator(); i.hasNext();) {
          GraphNode<N, E> node = i.next();
          if (subgraph.isIndependentOf(node.getValue())) {
            subgraph.addNode(node.getValue());
            node.setAnnotation(color);
            i.remove();
          }
        }
        count++;
      } while (!worklist.isEmpty());
      @SuppressWarnings("unchecked")
      N[] map = (N[]) new Object[count];
      colorToNodeMap = map;
      return count;
    }