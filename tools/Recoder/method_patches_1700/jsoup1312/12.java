    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return;
        } else {
            return false;
        }
    }