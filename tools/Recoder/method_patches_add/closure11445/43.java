  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_DEFINE) == typefield)){      return type;
    } else {
      return null;
    }
  }