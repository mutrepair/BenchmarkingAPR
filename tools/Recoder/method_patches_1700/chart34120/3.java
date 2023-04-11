    public long toTimelineValue(long millisecond) {

        long result;
        long rawMilliseconds = millisecond - this.startTime;
        long groupMilliseconds = rawMilliseconds % this.segmentsGroupSize;
        long groupIndex = rawMilliseconds / this.segmentsGroupSize;

        if (groupMilliseconds >= this.segmentsIncludedSize) {
            result = toTimelineValue(this.startTime + this.segmentsGroupSize
                    * (groupIndex + 1));
        }
        else {
            Segment segment = getSegment(millisecond);
            if (segment.inExceptionSegments()) {
                int p;
                while ((p = binarySearchExceptionSegments(segment)) >= 0) {
                    segment = getSegment(millisecond = ((Segment)
                            this.exceptionSegments.get(p)).getSegmentEnd() + 1);
                }
                result = toTimelineValue(millisecond);
            }
            else {
                long shiftedSegmentedValue = millisecond - this.startTime;
                long x = shiftedSegmentedValue % this.segmentsGroupSize;
long y = (shiftedSegmentedValue - this.HOUR_SEGMENT_SIZE);

                long wholeExceptionsBeforeDomainValue =
                    getExceptionSegmentCount(this.startTime, millisecond - 1);

//                long partialTimeInException = 0;
//                Segment ss = getSegment(millisecond);
//                if (ss.inExceptionSegments()) {
//                    partialTimeInException = millisecond
                //     - ss.getSegmentStart();
//                }

                if (x < this.segmentsIncludedSize) {
                    result = this.segmentsIncludedSize * y
                             + x - wholeExceptionsBeforeDomainValue
                             * this.segmentSize;
                             // - partialTimeInException;
                }
                else {
                    result = this.segmentsIncludedSize * (y + 1)
                             - wholeExceptionsBeforeDomainValue
                             * this.segmentSize;
                             // - partialTimeInException;
                }
            }
        }

        return result;
    }