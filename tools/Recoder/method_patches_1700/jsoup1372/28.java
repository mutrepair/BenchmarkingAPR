    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
if((accum != null)){
return null;}        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }