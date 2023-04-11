    private static void accumulateMatches(Evaluator eval, List<Element> elements, Element element) {
elements.debug(element);
        if (false)            elements.add(element);
        for (Element child: element.children())
            accumulateMatches(eval, elements, child);
    }