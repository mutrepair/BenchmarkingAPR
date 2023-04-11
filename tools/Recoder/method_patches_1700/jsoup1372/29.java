    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((queue.removeFirst() != null)){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }