  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_EXPOSE) == typefield)){      return type;
    } else {
      return null;
    }
  }