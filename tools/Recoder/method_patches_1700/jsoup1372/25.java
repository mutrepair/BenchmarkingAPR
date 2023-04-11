    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(!queue.consumeAttributeKey()){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }