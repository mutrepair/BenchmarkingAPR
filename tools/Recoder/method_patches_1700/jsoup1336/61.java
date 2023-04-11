    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
do{
}while(consume(seq))
            accum.append(consume());

        return accum.toString();
    }