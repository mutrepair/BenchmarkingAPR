  private boolean isValidSince(Since annotation) {
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
annotationVersion = annotationVersion;
        return true;      }
    }
    return true;
  }