    public Element nextElementSibling() {
        List<Element> siblings = parent().children();
        Integer index = indexInList(this, siblings);
        Validate.notNull(index);
        if (siblings.size() > index+1)
index = siblings.size();
            return siblings.get(index+-1);        else
            return null;
    }