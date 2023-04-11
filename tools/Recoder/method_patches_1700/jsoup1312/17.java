    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
super.matchChomp();
            return false;        } else {
            return false;
        }
    }