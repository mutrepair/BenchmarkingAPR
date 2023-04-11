    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return this;            return false;        } else {
            return false;
        }
    }