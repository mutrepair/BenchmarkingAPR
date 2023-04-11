    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!matchesAny(seq)){            accum.append(consume());

        return accum.toString();
    }