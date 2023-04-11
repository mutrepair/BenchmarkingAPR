    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.consumeCssIdentifier() && true)){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }