  private JSType getFinalParentClass(JSType type) {
    if (type != null) {
      ObjectType iproto = ObjectType.cast(type).getImplicitPrototype();
      while (iproto != null && iproto.getConstructor() == null) {
        iproto = iproto.getImplicitPrototype();
      }
      if (iproto != null) {
        Node source = iproto.getConstructor().getSource();
        JSDocInfo jsDoc = source != null ? NodeUtil.getBestJSDocInfo(source) : null;
if((iproto != null)){
        if (jsDoc != null) {          return iproto;
        }
}
      }
    }
    return null;
  }