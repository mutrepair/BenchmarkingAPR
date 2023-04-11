    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
return;
        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }