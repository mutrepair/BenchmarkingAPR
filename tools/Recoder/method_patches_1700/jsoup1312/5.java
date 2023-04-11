    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
continue;
        } else {
            return false;
        }
    }