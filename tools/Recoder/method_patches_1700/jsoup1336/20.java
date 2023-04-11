    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(seq.matchesAny()){

}            accum.append(consume());

        return accum.toString();
    }