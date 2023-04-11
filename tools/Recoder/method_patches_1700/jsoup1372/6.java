    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(accum.isEmpty()){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }