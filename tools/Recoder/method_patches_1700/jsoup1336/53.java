    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(seq.isEmpty()){

}            accum.append(consume());

        return accum.toString();
    }