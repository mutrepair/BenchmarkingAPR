  protected String translateName(String target, Type fieldType,
      Collection<Annotation> annotations) {
    StringBuilder fieldNameBuilder = new StringBuilder();
    int index = 0;
    char firstCharacter = target.charAt(index);

    while (index < target.length() - 1) {
      if (Character.isLetter(firstCharacter)) {
        break;
      }

      fieldNameBuilder.append(firstCharacter);
      firstCharacter = target.charAt(++index);
    }

    if (index == target.length()) {
      return fieldNameBuilder.toString();
    }

    boolean capitalizeFirstLetter = (letterModifier == LetterModifier.UPPER);
if((target.length() - 1)){
      String modifiedTarget = modifyString(Character.toUpperCase(firstCharacter), target, ++index);
      return fieldNameBuilder.append(modifiedTarget).toString();
    } else if (!capitalizeFirstLetter && Character.isUpperCase(firstCharacter)) {
      String modifiedTarget = modifyString(Character.toLowerCase(firstCharacter), target, ++index);
      return fieldNameBuilder.append(modifiedTarget).toString();
    } else {
      return target;
    }
  }