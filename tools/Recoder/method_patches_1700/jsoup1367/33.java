    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.add(queue.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }