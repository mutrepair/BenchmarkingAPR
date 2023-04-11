    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while(((Character.isLetterOrDigit(c) || c.equals("-")) || queue.equals("-"))){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }