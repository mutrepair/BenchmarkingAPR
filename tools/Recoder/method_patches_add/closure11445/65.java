  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_TYPEFIELD) == typefield)){      return type;
    } else {
      return null;
    }
  }