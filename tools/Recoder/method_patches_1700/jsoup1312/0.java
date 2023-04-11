    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return true;        } else {
            return false;
        }
    }