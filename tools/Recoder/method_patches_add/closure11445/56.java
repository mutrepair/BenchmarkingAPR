  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_EXTERNS) == typefield)){      return type;
    } else {
      return null;
    }
  }