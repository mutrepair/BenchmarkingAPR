  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_DICT) == typefield)){      return type;
    } else {
      return null;
    }
  }