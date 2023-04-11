    private static void accumulateMatches(Evaluator eval, List<Element> elements, Element element) {
if(element.isEmpty()){
            elements.add(element);
        for (Element child: element.children())
            accumulateMatches(eval, elements, child);
    }