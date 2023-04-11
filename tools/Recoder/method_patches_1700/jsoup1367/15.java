    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.isLetterOrDigit(accum.peek()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }