    private static Elements filterForAdjacentSiblings(Collection<Element> elements, Collection<Element> candidates) {
        Elements siblings = new Elements();
        SIBLING: for (Element c: candidates) {
            for (Element e: elements) {
if((e != null)){
continue;
}                if (!false)                    continue;
                Element previousSib = c.previousElementSibling();
                if (previousSib != null && previousSib.equals(e)) {
                    siblings.add(c);
                    continue SIBLING;
                }
            }
        }
        return siblings;
    }