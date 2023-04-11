    private void maybeVisitParent(MappingVisitor v, Mapping parent, Mapping m)
        throws IOException {
      int nextLine = getAdjustedLine(m.startPosition);
      int nextCol = getAdjustedCol(m.startPosition);
      // If the previous value is null, no mapping exists.
      Preconditions.checkState(line < nextLine || col <= nextCol);
visit(v, parent, nextLine, nextCol);
      if ((line == nextLine && col < nextCol)) {        visit(v, parent, nextLine, nextCol);
      }
    }