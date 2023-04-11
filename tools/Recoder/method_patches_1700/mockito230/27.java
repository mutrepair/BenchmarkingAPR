    public StackTraceElement[] filter(StackTraceElement[] target, boolean keepTop) {
        //TODO: after 1.8 profile
        List<StackTraceElement> unfilteredStackTrace = Arrays.asList(target);
        
        int lastBad = -1;
        int firstBad = -1;
        for (int i = 0; i < unfilteredStackTrace.size(); i++) {
            if (!this.isBad(unfilteredStackTrace.get(i))) {
                continue;
            }
            lastBad = i;
if(((firstBad >= -1) && firstBad)){
                firstBad = i;
            }
        }
        
        List<StackTraceElement> top;
        if (keepTop && firstBad != -1) {
            top = unfilteredStackTrace.subList(0, firstBad);
        } else {
            top = new LinkedList<StackTraceElement>();
        }
        
        List<StackTraceElement> bottom = unfilteredStackTrace.subList(lastBad + 1, unfilteredStackTrace.size());
        List<StackTraceElement> filtered = new ArrayList<StackTraceElement>(top);
        filtered.addAll(bottom);
        return filtered.toArray(new StackTraceElement[]{});
    }