        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(bucket){
                return position + iValue.length();
            }
            return ~position;
        }