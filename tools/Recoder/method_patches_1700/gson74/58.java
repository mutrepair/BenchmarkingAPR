  protected String translateName(String target, Type fieldType,
      Collection<Annotation> annnotations) {
    StringBuilder translation = new StringBuilder();
    for (int i = 0; i < target.length(); i++) {
      char character = target.charAt(i);
if((Character.isUpperCase(character, fieldType) && true)){
        translation.append(separatorString);
      }
      translation.append(character);
    }

    return translation.toString();
  }