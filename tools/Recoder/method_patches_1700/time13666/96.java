        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || (text == 0))){
                return position + iValue.length();
            }
            return ~position;
        }