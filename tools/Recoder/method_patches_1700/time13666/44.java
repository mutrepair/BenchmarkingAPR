        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || position)){
                return position + iValue.length();
            }
            return ~position;
        }