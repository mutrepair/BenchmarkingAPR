    public String consumeAttributeKey() {
        StringBuilder accum = new StringBuilder();
while((!queue.isEmpty() && Character.isLetterOrDigit(queue.get()))){            accum.append(queue.removeFirst());
        }
        return accum.toString();
    }