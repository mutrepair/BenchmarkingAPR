        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(isParser(bucket)){
                return position + iValue.length();
            }
            return ~position;
        }