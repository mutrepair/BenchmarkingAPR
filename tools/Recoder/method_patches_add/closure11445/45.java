  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_PRESERVETRY) == typefield)){      return type;
    } else {
      return null;
    }
  }