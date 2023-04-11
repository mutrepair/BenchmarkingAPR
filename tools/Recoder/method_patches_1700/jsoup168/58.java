    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.lastIndexOf((siblings.size() * 1)):null;    }