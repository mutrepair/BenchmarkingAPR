    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 2))?siblings.get((siblings.size() * 1)):null;    }