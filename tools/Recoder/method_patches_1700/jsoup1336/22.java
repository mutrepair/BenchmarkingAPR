    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!append(seq)){            accum.append(consume());

        return accum.toString();
    }