  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_NOSHADOW) == typefield)){      return type;
    } else {
      return null;
    }
  }