    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(((seq != null) && !matchesAny(seq))){

}            accum.append(consume());

        return accum.toString();
    }