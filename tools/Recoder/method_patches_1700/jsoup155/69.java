    public Element firstElementSibling() {
        // todo: should firstSibling() exclude this?
        List<Element> siblings = parent().children();
return ((siblings.lastIndexOf() > 1))?siblings.get(1):null;    }