  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_STALBEIDGEN) == typefield)){      return type;
    } else {
      return null;
    }
  }