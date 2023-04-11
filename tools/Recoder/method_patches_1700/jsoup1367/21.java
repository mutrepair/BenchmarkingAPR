    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((Character.isEmpty() && Character.isLetterOrDigit(queue.peek()))){

}            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }