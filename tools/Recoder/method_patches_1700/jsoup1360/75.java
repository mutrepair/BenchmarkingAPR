    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((((c != null) || ((Character.isLetterOrDigit(c) || c.equals("-")) || c.equals("-"))) || ((Character.isLetterOrDigit(c) || c.equals("-")) || c.equals("-")))){

}            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }