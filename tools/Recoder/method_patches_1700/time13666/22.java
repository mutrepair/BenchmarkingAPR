        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(isParser(text)){
                return position + iValue.length();
            }
            return ~position;
        }