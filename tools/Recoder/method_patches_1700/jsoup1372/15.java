    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
accum.append(queue.removeFirst());
        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }