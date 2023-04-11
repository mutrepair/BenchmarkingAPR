    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
queue.removeFirst();
        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }