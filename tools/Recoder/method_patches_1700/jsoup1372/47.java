    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while(!queue.removeFirst()){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }