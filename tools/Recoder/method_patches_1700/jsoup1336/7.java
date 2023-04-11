    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
while(addFirst(seq)){

}            accum.append(consume());

        return accum.toString();
    }