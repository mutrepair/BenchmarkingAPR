    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while(((Character.toString(c) || c.equals("-")) || c.equals("-"))){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }