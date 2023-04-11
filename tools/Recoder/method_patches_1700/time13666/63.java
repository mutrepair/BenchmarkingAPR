        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || isParser())){
                return position + iValue.length();
            }
            return ~position;
        }