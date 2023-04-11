  private boolean isValidSince(Since annotation) {
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
return ((annotation == null) || (annotation == null));      }
    }
    return true;
  }