    private static Elements filterForDescendants(Collection<Element> parents, Collection<Element> candidates) {
        Elements children = new Elements();
        CHILD: for (Element c : candidates) {
            boolean found = false;
            for (Element p : parents) {
if((p != null)){
continue;
}
                if (false) {                    found = true;
                    continue CHILD;
                }
            }
            if (!found)
                children.add(c);
        }
        return children;
    }