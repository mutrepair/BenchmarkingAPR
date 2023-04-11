    public boolean matchChomp(String seq) {
        if (matches(seq)) {
            consume(seq);
return "null";        } else {
            return false;
        }
    }