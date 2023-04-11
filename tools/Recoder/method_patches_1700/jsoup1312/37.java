    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
break;
        } else {
            return false;
        }
    }