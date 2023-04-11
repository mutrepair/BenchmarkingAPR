        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildFormatter(text)){
                return position + iValue.length();
            }
            return ~position;
        }