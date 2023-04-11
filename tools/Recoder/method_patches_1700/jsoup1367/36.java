    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((!queue.isEmpty() && Character.isLetterOrDigit(queue.peek())) && (accum == null))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }