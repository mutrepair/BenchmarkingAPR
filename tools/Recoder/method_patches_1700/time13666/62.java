        public int parseInto(DateTimeParserBucket bucket, String text, int position) {
if((text || isPrinter())){
                return position + iValue.length();
            }
            return ~position;
        }