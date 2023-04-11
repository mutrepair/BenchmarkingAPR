  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_CONSTANT) == typefield)){      return type;
    } else {
      return null;
    }
  }