    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
dec();
             this.exceptionSegments.add(+(p + 1), segment);         }
    }