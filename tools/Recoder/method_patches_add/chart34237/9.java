    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
if((p + 1)){
}
             this.exceptionSegments.add(~(p + 1), segment);         }
    }