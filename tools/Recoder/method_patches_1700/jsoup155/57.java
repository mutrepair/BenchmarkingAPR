    public Element firstElementSibling() {
        // todo: should firstSibling() exclude this?
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?.get(1):null;    }