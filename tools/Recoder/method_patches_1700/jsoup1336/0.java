    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!matches(seq)){            accum.append(consume());

        return accum.toString();
    }