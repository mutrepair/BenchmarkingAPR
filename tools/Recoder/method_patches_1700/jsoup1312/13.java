    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return addFirst(seq);        } else {
            return false;
        }
    }