    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
if((m && CapturesArguments)){                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }