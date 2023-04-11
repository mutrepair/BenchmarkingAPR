    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() == siblings))?siblings.get((siblings.size() - 1)):null;    }