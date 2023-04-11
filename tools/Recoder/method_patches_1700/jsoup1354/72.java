    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while(((((c.equals("-") && (c == null)) && c.equals("-")) && (c.equals("-") || c.equals("-"))) && (c.equals("-") || c.equals("-")))){

}            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }