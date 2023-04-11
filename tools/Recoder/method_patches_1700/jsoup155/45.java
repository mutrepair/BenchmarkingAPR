    public Element firstElementSibling() {
        // todo: should firstSibling() exclude this?
        List<Element> siblings = parent().children();
return ((siblings == null))?siblings.get(1):null;    }