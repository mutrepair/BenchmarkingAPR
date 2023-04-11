    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.isEmpty() && null)){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }