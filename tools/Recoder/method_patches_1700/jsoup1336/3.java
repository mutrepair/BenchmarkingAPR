    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(!matchesAny(accum)){            accum.append(consume());

        return accum.toString();
    }