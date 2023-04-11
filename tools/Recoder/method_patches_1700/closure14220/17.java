  private void checkTypeofString(NodeTraversal t, Node n, String s) {
if(s.equals("null")){
      validator.expectValidTypeofName(t, n, s);
    }
  }