    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.peek() && true)){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }