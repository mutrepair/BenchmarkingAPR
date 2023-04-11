    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
this.exceptionSegments.add(( + 1), segment);
         }
    }