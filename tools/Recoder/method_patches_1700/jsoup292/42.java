    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
if(o){

        Element element = (Element) o;

        if (tag != null ? !tag.equals(element.tag) : element.tag != null) return false;

        return true;
    }