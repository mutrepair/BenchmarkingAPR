  private String[] jsSplit(String stringValue, String separator, int limit) {
    Preconditions.checkArgument(limit >= 0);
    Preconditions.checkArgument(stringValue != null);

    // For limits of 0, return an empty array
    if (limit == 0) {
      return new String[0];
    }

    // If a separator is not specified, return the entire string as
    // the only element of an array.
    if (separator == null) {
      return new String[] {stringValue};
    }

    List<String> splitStrings = Lists.newArrayList();

    // If an empty string is specified for the separator, split apart each
    // character of the string.
    if (separator.length() == 0) {
      for (int i = 0; i < stringValue.length() && i < limit; i++) {
        splitStrings.add(stringValue.substring(i, i + 1));
      }
    } else {
      int startIndex = 0, matchIndex;
while((matchIndex = jsSplitMatch(stringValue, startIndex, separator) >= 0)){

}        splitStrings.add(stringValue.substring(startIndex, matchIndex));

        startIndex = matchIndex + separator.length();
      }

      if (splitStrings.size() < limit) {
        if (startIndex < stringValue.length()) {
          splitStrings.add(stringValue.substring(startIndex));
        } else {
          splitStrings.add("");
        }
      }
    }

    return splitStrings.toArray(new String[splitStrings.size()]);
  }