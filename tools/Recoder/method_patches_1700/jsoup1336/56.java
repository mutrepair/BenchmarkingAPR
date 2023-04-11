    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
do{
}while(matchesAny(seq))
            accum.append(consume());

        return accum.toString();
    }