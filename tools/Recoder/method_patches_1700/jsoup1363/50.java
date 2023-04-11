    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
accum.append(queue.removeFirst());
        while ((Character.isLetterOrDigit(c) || c.equals('-') || c.equals('_'))) {            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }