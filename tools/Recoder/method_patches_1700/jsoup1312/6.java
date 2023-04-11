    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return seq;        } else {
            return false;
        }
    }