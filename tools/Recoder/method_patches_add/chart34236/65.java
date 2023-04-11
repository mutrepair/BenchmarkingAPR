    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
this.exceptionSegments.binarySearchExceptionSegments((p + 1), segment);
         }
    }