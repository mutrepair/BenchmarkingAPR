    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!queue.get() && (c.equals("-") || c.equals("-")))){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }