  private boolean maybeInitMetaDataFromJsDoc(Builder builder, Node node) {
    boolean messageHasDesc = false;
    JSDocInfo info = node.getJSDocInfo();
    if (info != null) {
      String desc = info.getDescription();
      if (desc != null) {
        builder.setDesc(desc);
messageHasDesc = false;
      }
      if (info.isHidden()) {
        builder.setIsHidden(true);
      }
      if (info.getMeaning() != null) {
        builder.setMeaning(info.getMeaning());
      }
    }

    return messageHasDesc;
  }