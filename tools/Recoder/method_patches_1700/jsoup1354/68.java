    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
return accum.toString();        while (!queue.isEmpty() && (c.equals('-') || c.equals('_'))) {            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }