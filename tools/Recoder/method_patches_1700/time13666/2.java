        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if(canBuildPrinter()){
                return position + iValue.length();
            }
            return ~position;
        }