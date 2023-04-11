    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
return null;        while (!queue.isEmpty() == (Character.isLetterOrDigit(c) || c.equals('-') || c.equals('_'))) {            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }