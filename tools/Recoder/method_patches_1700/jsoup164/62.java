    public Element lastElementSibling() {
        List<Element> siblings = parent().children();
return ((siblings == 1))?siblings:null;    }