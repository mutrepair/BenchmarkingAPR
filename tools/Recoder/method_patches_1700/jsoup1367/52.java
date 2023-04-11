    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(((!queue.isEmpty() && (queue.peek() != null)) && Character.isLetterOrDigit(queue.peek()))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }