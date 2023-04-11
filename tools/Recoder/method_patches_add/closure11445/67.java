  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> TYPEFIELD_RETURN) == typefield)){      return type;
    } else {
      return null;
    }
  }