    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
if((accum != null)){
        while (!matchesAny(seq))            accum.append(consume());

        return accum.toString();
}
    }