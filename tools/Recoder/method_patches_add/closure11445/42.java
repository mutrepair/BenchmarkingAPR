  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_CONSTRUCTOR) == typefield)){      return type;
    } else {
      return null;
    }
  }