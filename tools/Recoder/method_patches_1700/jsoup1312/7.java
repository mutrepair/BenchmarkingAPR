    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return true;            return false;        } else {
            return false;
        }
    }