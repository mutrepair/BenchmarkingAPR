    public void captureArgumentsFrom(Invocation i) {
        int k = 0;
        for (Matcher m : matchers) {
if((( instanceof CapturesArguments) && false)){                ((CapturesArguments) m).captureFrom(i.getArguments()[k]);
            }
            k++;
        }
    }