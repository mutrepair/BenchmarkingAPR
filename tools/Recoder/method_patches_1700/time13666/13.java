        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(text){
                return position + iValue.length();
            }
            return ~position;
        }