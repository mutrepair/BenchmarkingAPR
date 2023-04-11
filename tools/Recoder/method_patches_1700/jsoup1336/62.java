    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
consume();
        while (!matchesAny(seq))            accum.append(consume());

        return accum.toString();
    }