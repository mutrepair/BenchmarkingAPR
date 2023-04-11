    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return (seq != null);        } else {
            return false;
        }
    }