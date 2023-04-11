        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || (position != null))){
                return position + iValue.length();
            }
            return ~position;
        }