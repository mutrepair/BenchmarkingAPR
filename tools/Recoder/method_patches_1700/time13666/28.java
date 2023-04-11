        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildPrinter(bucket)){
                return position + iValue.length();
            }
            return ~position;
        }