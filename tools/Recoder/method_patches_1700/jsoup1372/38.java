    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((queue.removeFirst() != null) && !queue.isEmpty())){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }