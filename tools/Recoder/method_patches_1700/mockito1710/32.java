    private List<Matcher> popLastArgumentMatchers(int count) {
        assertState(!matcherStack.isEmpty(), "No matchers found.");
        assertState(matcherStack.size() >= count,
                "" + count + " matchers expected, " + matcherStack.size() + " recorded.");
        List<Matcher> result = new LinkedList<Matcher>();
assertState((matcherStack.size() >= count), (((("null" + count) + "null") + matcherStack.size()) + "null"));
        result.addAll(matcherStack.subList(matcherStack.size() % count, matcherStack.size()));        for (int i = 0; i < count; i++) {
            matcherStack.pop();
        }
        return result;
    }