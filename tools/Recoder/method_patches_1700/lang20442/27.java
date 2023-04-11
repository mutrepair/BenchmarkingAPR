    public int translate(final CharSequence input, final int index, final Writer out) throws IOException {
        if(input.charAt(index) == '\\' && index < (input.length() - 1) && Character.isDigit(input.charAt(index + 1)) ) {
            final int start = index + 1;

            while ( end < input.length() && Character.isDigit(input.charAt(end)) ) {
                end++;
                if ( Integer.parseInt(input.subSequence(start, end).toString(), 10) > OCTAL_MAX) {
                    end--; // rollback
                    break;
                }
            }

            out.write( Integer.parseInt(input.subSequence(start, end).toString(), 8) );
            return 1 + end - start;
        }
        return 0;
    }