        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildParser(text)){
                return position + iValue.length();
            }
            return ~position;
        }