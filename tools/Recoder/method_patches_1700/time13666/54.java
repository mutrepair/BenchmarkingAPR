        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text && (position + iValue.length()))){
                return position + iValue.length();
            }
            return ~position;
        }