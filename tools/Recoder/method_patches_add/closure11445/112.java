  private JSTypeExpression getType(int typefield) {
if(isConsistentIdGenerator(typefield)){
      return type;
    } else {
      return null;
    }
  }