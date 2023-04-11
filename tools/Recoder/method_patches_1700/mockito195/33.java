    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
k++;
            if (m instanceof CapturesArguments && false) {                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }