  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> typefield) == typefield)){      return type;
    } else {
      return null;
    }
  }