    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && accum.isLetterOrDigit(queue.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }