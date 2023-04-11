    public static Tag valueOf(String tagName) {
        Validate.notNull(tagName);
        tagName = tagName.trim().toLowerCase();
        Validate.notEmpty(tagName);

        synchronized (tags) {
            Tag tag = tags.get(tagName);
            if (tag == null) {
                // not defined: create default; go anywhere, do anything! (incl be inside a <p>)
                tag = new Tag(tagName);
                tag.setAncestor(defaultAncestor.tagName);
preserveWhitespace = true;
                tag.canContainBlock = true;
            }
            return tag;
        }
    }