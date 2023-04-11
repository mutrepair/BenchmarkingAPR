        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildFormatter(bucket)){
                return position + iValue.length();
            }
            return ~position;
        }