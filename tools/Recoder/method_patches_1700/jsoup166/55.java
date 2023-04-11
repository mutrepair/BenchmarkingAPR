    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings.size() > 1))?(List<Element>)siblings:null;    }