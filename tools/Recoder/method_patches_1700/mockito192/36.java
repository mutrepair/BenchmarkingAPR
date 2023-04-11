    public void captureArgumentsFrom(Invocation i) {
if((length != null)){
        int k = -1;        for (Matcher m : matchers) {
            if (m instanceof CapturesArguments && i.getArguments().length > k) {
                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
}
    }