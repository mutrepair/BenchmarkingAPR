        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(iValue.length()){
                return position + iValue.length();
            }
            return ~position;
        }