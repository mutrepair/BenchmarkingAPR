    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return this;        } else {
            return false;
        }
    }