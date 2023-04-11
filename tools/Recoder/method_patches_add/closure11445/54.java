  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_IMPLICITCAST) == typefield)){      return type;
    } else {
      return null;
    }
  }