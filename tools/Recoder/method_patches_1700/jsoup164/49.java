    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings == null))?siblings.get((siblings.size() - 1)):null;    }