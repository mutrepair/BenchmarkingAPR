    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((accum == null) && !queue.isEmpty())){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }