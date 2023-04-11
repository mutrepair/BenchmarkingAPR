    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
if((accum != null)){
        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
}
    }