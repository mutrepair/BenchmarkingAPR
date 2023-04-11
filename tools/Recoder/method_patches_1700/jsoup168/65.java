    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.e((siblings.size() * 1)):null;    }