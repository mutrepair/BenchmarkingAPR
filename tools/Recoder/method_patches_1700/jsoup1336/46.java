    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while((!matchesAny(seq) == null)){

}            accum.append(consume());

        return accum.toString();
    }