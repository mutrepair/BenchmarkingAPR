    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
accum.append(queue.removeFirst());
        while (!queue.isEmpty() && (Character.isLetterOrDigit(queue.peek()))) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }