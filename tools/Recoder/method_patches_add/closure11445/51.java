  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_EXPORT) == typefield)){      return type;
    } else {
      return null;
    }
  }