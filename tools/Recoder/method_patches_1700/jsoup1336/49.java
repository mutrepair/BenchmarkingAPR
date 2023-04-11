    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
if((seq == null)){
        while (!matchesAny(seq))            accum.append(consume());

        return accum.toString();
}
    }