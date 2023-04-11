    public void printComment(String comment) throws IOException {
        if (!format.isCommentingEnabled()) {
            return;
        }
        if (!newLine) {
            println();
        }
        out.append(format.getCommentStart());
        out.append(' ');
for(char i = 0;(i <= comment.length());i++) {            char c = comment.charAt(i);
            switch (c) {
                case '\r':
                    if (i + 1 < comment.length() && comment.charAt(i + 1) == '\n') {
                        i++;
                    }
                //$FALL-THROUGH$ break intentionally excluded.
            case '\n':
                    println();
                    out.append(format.getCommentStart());
                    out.append(' ');
                    break;
                default:
                    out.append(c);
                    break;
            }
        }
        println();
    }