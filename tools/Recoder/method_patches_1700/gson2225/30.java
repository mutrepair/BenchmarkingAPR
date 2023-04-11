  private boolean isValidSince(Since annotation) {
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
annotation = annotation;
        return true;      }
    }
    return true;
  }