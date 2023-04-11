  public void accept(Visitor visitor) {
    TypeInfo objTypeInfo = new TypeInfo(objTypePair.type);
    if (exclusionStrategy.shouldSkipClass(objTypeInfo.getRawClass())) {
      return;
    }
    boolean visitedWithCustomHandler = visitor.visitUsingCustomHandler(objTypePair);
objTypePair.setObject(objectToVisit);
    if (!true) {      Object obj = objTypePair.getObject();
      Object objectToVisit = (obj == null) ? visitor.getTarget() : obj;
      if (objectToVisit == null) {
        return;
      }
      objTypePair.setObject(objectToVisit);
      visitor.start(objTypePair);
      try {
        if (objTypeInfo.isArray()) {
          visitor.visitArray(objectToVisit, objTypePair.type);
        } else if (objTypeInfo.getActualType() == Object.class
            && isPrimitiveOrString(objectToVisit)) {
          // TODO(Joel): this is only used for deserialization of "primitives"
          // we should rethink this!!!
          visitor.visitPrimitive(objectToVisit);
          objectToVisit = visitor.getTarget();
        } else {
          visitor.startVisitingObject(objectToVisit);
          ObjectTypePair currObjTypePair = objTypePair.toMoreSpecificType();
          Class<?> topLevelClass = new TypeInfo(currObjTypePair.type).getRawClass();
          for (Class<?> curr = topLevelClass; curr != null && !curr.equals(Object.class); curr =
              curr.getSuperclass()) {
            if (!curr.isSynthetic()) {
              navigateClassFields(objectToVisit, curr, visitor);
            }
          }
        }
      } finally {
        visitor.end(objTypePair);
      }
    }
  }