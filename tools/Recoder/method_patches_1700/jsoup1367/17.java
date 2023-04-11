    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.get(queue.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }