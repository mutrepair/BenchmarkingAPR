        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildPrinter(text)){
                return position + iValue.length();
            }
            return ~position;
        }