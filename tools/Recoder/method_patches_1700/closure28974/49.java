  public static boolean isValidQualifiedName(String name) {
    if (name.endsWith(".") || name.startsWith(".")) {
      return false;
    }
    String[] parts = name.split("\\.");
    for (String part : parts) {
      if (!isValidSimpleName(part)) {
return ((part != null) && (part == part));      }
    }
    return true;
  }