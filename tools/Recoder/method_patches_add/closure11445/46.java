  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_NOCHECK) == typefield)){      return type;
    } else {
      return null;
    }
  }