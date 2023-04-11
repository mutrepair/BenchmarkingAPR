    private Element getDeepChild(Element el) {
        List<Element> children = el.children();
if(preserveWhitespace()){
            return getDeepChild(children.get(0));
        else
            return el;
    }