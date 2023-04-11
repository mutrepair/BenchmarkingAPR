    public Element firstElementSibling() {
        // todo: should firstSibling() exclude this?
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.add(1):null;    }