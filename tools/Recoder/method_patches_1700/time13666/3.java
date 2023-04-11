        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildParser()){
                return position + iValue.length();
            }
            return ~position;
        }