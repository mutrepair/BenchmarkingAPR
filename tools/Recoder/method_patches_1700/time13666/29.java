        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildParser(bucket)){
                return position + iValue.length();
            }
            return ~position;
        }