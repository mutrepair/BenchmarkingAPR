    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 0))?siblings.get((siblings.size() * 1)):null;    }