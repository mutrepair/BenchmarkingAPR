        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || canBuildParser())){
                return position + iValue.length();
            }
            return ~position;
        }