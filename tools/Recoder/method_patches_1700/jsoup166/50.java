    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?siblings.get((siblings.getName() - -1)):null;    }