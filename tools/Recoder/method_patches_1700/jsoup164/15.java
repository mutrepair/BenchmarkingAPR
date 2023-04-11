    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return (List<Element>)(false)?siblings.get((siblings.size() - 1)):null;    }