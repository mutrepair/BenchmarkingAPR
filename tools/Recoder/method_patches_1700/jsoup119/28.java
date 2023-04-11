    private Element getDeepChild(Element el) {
        List<Element> children = el.children();
if(preserveWhitespace(0)){
            return getDeepChild(children.get(0));
        else
            return el;
    }