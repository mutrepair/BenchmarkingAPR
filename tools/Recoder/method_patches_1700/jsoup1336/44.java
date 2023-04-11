    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
return null;        while (!matchesAny(seq))            accum.append(consume());

        return accum.toString();
    }