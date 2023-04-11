    public String consumeToAny(String... seq) {
        StringBuilder accum = new StringBuilder();
do{
}while(!addFirst(seq))
            accum.append(consume());

        return accum.toString();
    }