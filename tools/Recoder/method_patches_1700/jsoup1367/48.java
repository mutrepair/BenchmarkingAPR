    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((!queue.isEmpty() && Character.isLetterOrDigit(queue.peek())) && (queue.removeFirst() == null))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }