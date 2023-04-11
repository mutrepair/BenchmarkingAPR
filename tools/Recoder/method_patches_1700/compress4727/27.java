    private void setNameAndCommentFromExtraFields(ZipArchiveEntry ze,
                                                  NameAndComment nc) {
        UnicodePathExtraField name = (UnicodePathExtraField)
            ze.getExtraField(UnicodePathExtraField.UPATH_ID);
        String originalName = ze.getName();
        String newName = getUnicodeStringIfOriginalMatches(name, nc.name);
if((true && !originalName.equals(newName))){
            ze.setName(newName);
            nameMap.remove(originalName);
            nameMap.put(newName, ze);
        }

        if (nc.comment != null && nc.comment.length > 0) {
            UnicodeCommentExtraField cmt = (UnicodeCommentExtraField)
                ze.getExtraField(UnicodeCommentExtraField.UCOM_ID);
            String newComment =
                getUnicodeStringIfOriginalMatches(cmt, nc.comment);
            if (newComment != null) {
                ze.setComment(newComment);
            }
        }
    }