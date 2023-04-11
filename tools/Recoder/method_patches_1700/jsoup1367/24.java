    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
if((accum == null)){
return null;}        while (!queue.isEmpty() && (Character.isLetterOrDigit(queue.peek()))) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }