  private boolean isValidSince(Since annotation) {
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
annotation = annotationVersion;
        return true;      }
    }
    return true;
  }