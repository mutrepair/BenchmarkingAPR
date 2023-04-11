    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.isLetterOrDigit(queue.consumeAttributeKey()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }