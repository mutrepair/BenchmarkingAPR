    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && (accum == null))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }