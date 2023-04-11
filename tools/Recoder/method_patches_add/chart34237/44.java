    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
return;
             this.exceptionSegments.add(~(p + 1), segment);         }
    }