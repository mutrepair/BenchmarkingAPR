    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
(CapturesArguments)m;
            if (m instanceof CapturesArguments) {                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }