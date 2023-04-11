    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
break;
            if (m instanceof CapturesArguments) {                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }