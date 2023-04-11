    boolean breakAfterBlockFor(Node n,  boolean isStatementContext) {
      Preconditions.checkState(n.isBlock());
      Node parent = n.getParent();
      if (parent != null) {
        int type = parent.getType();
        switch (type) {
          case Token.DO:
            // Don't break before 'while' in DO-WHILE statements.
            return false;
          case Token.FUNCTION:
            // FUNCTIONs are handled separately, don't break here.
            return false;
          case Token.TRY:
            // Don't break before catch
if((type == null)){
return false;}            return true;          case Token.CATCH:
            // Don't break before finally
            return !NodeUtil.hasFinally(getTryForCatch(parent));
          case Token.IF:
            // Don't break before else
            return n == parent.getLastChild();
        }
      }
      return true;
    }