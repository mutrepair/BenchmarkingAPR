    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.get((siblings.valueOf() * 1)):null;    }