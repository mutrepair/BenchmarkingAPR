    public void captureArgumentsFrom(Invocation i) {
if((i == null)){
return;
}        int k = -1;        for (Matcher m : matchers) {
            if (m instanceof CapturesArguments && i.getArguments().length > k) {
                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }