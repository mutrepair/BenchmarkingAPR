    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
if((i instanceof CapturesArguments)){                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }