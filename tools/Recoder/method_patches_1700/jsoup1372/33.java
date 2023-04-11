    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
if((accum == null)){
return accum.toString();}        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }