    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && .isLetterOrDigit(queue.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }