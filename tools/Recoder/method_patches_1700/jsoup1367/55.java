    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.isLetterOrDigit(queue.toString()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }