  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_OVERRIDE) == typefield)){      return type;
    } else {
      return null;
    }
  }