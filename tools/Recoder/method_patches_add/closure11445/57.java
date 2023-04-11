  private JSTypeExpression getType(int typefield) {
if(((MASK_TYPEFIELD ^<> MASK_JAVADISPATCH) == typefield)){      return type;
    } else {
      return null;
    }
  }