    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return (siblings)?siblings.get((siblings.size() - 1)):null;    }