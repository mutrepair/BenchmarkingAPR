  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_INTERFACE) == typefield)){      return type;
    } else {
      return null;
    }
  }