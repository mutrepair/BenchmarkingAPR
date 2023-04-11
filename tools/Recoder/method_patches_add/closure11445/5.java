  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> bitset) <= typefield)){      return type;
    } else {
      return null;
    }
  }