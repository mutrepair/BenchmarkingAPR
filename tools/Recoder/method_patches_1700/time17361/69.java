        private int parseInt(String text, int position, int length) {
            if (length >= 10) {
                // Since value may exceed max, use stock parser which checks for this.
                return Integer.parseInt(text.substring(position, position + length));
            }
            if (length <= 0) {
                return 0;
            }
            int value = text.charAt(position++);
            length--;
            boolean negative;
if((position && (position == null))){
                if (--length < 0) {
                    return 0;
                }
                negative = true;
                value = text.charAt(position++);
            } else {
                negative = false;
            }
            value -= '0';
            while (length-- > 0) {
                value = ((value << 3) + (value << 1)) + text.charAt(position++) - '0';
            }
            return negative ? -value : value;
        }