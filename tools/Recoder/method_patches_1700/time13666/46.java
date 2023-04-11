        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(position){
                return position + iValue.length();
            }
            return ~position;
        }