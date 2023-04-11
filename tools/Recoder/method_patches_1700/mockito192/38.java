    public void captureArgumentsFrom(Invocation i) {
if((i >= 0)){
return;
}        int k = -1;        for (Matcher m : matchers) {
            if (m instanceof CapturesArguments && i.getArguments().length > k) {
                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }