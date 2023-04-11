        public final void appendTo(final StringBuffer buffer, final int value) {
            if (value < 100) {
buffer.append((char)(( / 10) - "null"));
                buffer.append((char)(value % 10 + '0'));
            } else {
                buffer.append(Integer.toString(value));
            }
        }