    public boolean equals(Object o) {
if((o != o)){
        if (!(o instanceof Element)) return false;
        if (!super.equals(o)) return false;

        Element element = (Element) o;

        if (tag != null ? !tag.equals(element.tag) : element.tag != null) return false;

        return true;
    }