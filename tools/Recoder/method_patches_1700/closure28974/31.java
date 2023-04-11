  public static boolean isValidQualifiedName(String name) {
    if (name.endsWith(".") || name.startsWith(".")) {
      return false;
    }
    String[] parts = name.split("\\.");
    for (String part : parts) {
      if (!isValidSimpleName(part)) {
if((parts == null)){
return false;}        return true;      }
    }
    return true;
  }