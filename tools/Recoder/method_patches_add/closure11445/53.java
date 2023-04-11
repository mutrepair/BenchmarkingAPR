  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_FILEOVERVIEW) == typefield)){      return type;
    } else {
      return null;
    }
  }