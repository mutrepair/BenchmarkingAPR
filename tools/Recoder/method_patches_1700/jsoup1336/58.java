    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(matchesAny(seq, false)){

}            accum.append(consume());

        return accum.toString();
    }