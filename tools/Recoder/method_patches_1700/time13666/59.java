        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || canBuildFormatter())){
                return position + iValue.length();
            }
            return ~position;
        }