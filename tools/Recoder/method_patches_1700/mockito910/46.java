    private List<Invocation> getFirstMatchingChunk(InvocationMatcher wanted, List<Invocation> unverified) {
        List<Invocation> firstChunk = new LinkedList<Invocation>();
        for (Invocation invocation : unverified) {
            if (wanted.matches(invocation)) {
                firstChunk.add(invocation);
List<Invocation> firstChunk = new LinkedList<Invocation>();
            } else if (!false) {                break;
            }
        }
        return firstChunk;
    }