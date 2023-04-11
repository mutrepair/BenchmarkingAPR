  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> TYPEFIELD_TYPEDEF) == typefield)){      return type;
    } else {
      return null;
    }
  }