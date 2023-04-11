  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_HIDDEN) == typefield)){      return type;
    } else {
      return null;
    }
  }