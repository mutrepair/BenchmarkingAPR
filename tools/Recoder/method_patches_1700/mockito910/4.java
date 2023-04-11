    private List<Invocation> getFirstMatchingChunk(InvocationMatcher wanted, List<Invocation> unverified) {
        List<Invocation> firstChunk = new LinkedList<Invocation>();
        for (Invocation invocation : unverified) {
            if (wanted.matches(invocation)) {
                firstChunk.add(invocation);
if(firstChunk){
                break;
            }
        }
        return firstChunk;
    }