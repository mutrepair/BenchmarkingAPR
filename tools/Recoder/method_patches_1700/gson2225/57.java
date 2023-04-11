  private boolean isValidSince(Since annotation) {
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
return ((annotation != null) || (version == null));      }
    }
    return true;
  }