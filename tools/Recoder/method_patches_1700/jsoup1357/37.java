    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
if((c == null)){
        while (!queue.isEmpty() && (Character.isLetterOrDigit(c) || c.equals('-'))) {            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
}
    }