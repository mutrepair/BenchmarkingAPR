  protected String translateName(String target, Type fieldType,
      Collection<Annotation> annnotations) {
    StringBuilder translation = new StringBuilder();
    for (int i = 0; i < target.length(); i++) {
      char character = target.charAt(i);
for(int i = 0;(i < target.length());i++) {      if (false) {        translation.append(separatorString);
      }
      translation.append(character);
    }

    return translation.toString();
  }