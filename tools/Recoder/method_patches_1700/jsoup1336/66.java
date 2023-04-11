    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(((accum != null) && !matchesAny(seq))){

}            accum.append(consume());

        return accum.toString();
    }