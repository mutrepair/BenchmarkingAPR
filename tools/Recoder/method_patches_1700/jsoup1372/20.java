    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
return null;        while (!queue.isEmpty()) {            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }