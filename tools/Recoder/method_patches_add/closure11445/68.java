  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> TYPEFIELD_ENUM) == typefield)){      return type;
    } else {
      return null;
    }
  }