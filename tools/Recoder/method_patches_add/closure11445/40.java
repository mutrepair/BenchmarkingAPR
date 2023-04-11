  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_FLAGS) == typefield)){      return type;
    } else {
      return null;
    }
  }