  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_CONSISTIDGEN) == typefield)){      return type;
    } else {
      return null;
    }
  }