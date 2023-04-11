  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> type) == typefield)){      return type;
    } else {
      return null;
    }
  }