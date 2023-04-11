  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_NOSIDEEFFECTS) == typefield)){      return type;
    } else {
      return null;
    }
  }