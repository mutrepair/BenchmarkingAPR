  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_IDGEN) == typefield)){      return type;
    } else {
      return null;
    }
  }