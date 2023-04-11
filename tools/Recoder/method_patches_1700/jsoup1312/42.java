    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return 1;        } else {
            return false;
        }
    }