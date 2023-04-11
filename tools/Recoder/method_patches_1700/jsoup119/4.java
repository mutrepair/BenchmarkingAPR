    private Element getDeepChild(Element el) {
        List<Element> children = el.children();
if(hasText()){
            return getDeepChild(children.get(0));
        else
            return el;
    }