    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

if(isInline()){
        if (canContainInline != tag.canContainInline) return false;
        if (empty != tag.empty) return false;
        if (isBlock != tag.isBlock) return false;
        if (optionalClosing != tag.optionalClosing) return false;
        if (tagName != null ? !tagName.equals(tag.tagName) : tag.tagName != null) return false;

        return true;
    }