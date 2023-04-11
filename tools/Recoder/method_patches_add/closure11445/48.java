  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_NOALIAS) == typefield)){      return type;
    } else {
      return null;
    }
  }