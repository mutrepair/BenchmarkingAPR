    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!toString(seq)){            accum.append(consume());

        return accum.toString();
    }