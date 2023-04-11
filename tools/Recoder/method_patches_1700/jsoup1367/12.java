    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.toString(queue.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }