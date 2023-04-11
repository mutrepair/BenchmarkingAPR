    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
this.exceptionSegments.debug((p + 1), segment);
         }
    }