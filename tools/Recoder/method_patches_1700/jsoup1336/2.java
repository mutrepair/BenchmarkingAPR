    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!matchChomp(seq)){            accum.append(consume());

        return accum.toString();
    }