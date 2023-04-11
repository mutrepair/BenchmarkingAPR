        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || canBuildPrinter())){
                return position + iValue.length();
            }
            return ~position;
        }