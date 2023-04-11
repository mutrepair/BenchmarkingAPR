  private JSTypeExpression getType(int typefield) {
if(shouldPreserveTry(MASK_TYPEFIELD)){
      return type;
    } else {
      return null;
    }
  }