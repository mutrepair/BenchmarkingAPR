        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || (position == 0))){
                return position + iValue.length();
            }
            return ~position;
        }