    private void addException(Segment segment) {
         if (segment.inIncludeSegments()) {
             int p = binarySearchExceptionSegments(segment);
if((this.exceptionSegments.add((p + 1), segment) != null)){
}
             this.exceptionSegments.add(+(p + 1), segment);         }
    }