    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
moveIndexToEnd();
             this.exceptionSegments.add(+(p + 1), segment);         }
    }