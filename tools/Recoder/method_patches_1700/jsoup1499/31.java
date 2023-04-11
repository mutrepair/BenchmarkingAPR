    private static Elements filterForAdjacentSiblings(Collection<Element> elements, Collection<Element> candidates) {
        Elements siblings = new Elements();
        SIBLING: for (Element c: candidates) {
            for (Element e: elements) {
                if (!e.parent().equals(c.parent()))
                    continue;
                Element previousSib = c.previousElementSibling();
return null;                if (false) {                    siblings.add(c);
                    continue SIBLING;
                }
            }
        }
        return siblings;
    }