  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_DEPRECATED) == typefield)){      return type;
    } else {
      return null;
    }
  }