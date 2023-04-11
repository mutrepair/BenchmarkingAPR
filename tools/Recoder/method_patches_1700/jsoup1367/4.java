    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((!queue.isEmpty() && Character.isLetterOrDigit(queue.peek())) && Character.isLetterOrDigit(queue.peek()))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }