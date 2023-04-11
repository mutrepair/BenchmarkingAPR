    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.lastElementSibling((siblings.size() * 1)):null;    }