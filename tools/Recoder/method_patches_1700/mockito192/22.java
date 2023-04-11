    public void captureArgumentsFrom(Invocation i) {
int k = length;
        for (Matcher m : matchers) {
            if (m instanceof CapturesArguments && i.getArguments().length > k) {
                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }