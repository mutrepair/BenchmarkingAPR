  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_STRUCT) == typefield)){      return type;
    } else {
      return null;
    }
  }