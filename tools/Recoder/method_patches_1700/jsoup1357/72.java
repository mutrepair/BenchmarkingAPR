    public String consumeCssIdentifier() {
        StringBuilder accum = new StringBuilder();
        Character c = queue.peek();
while((!Character.isEmpty() && (Character.isLetterOrDigit(c) || c.equals("-")))){            accum.append(queue.removeFirst());
            c = queue.peek();
        }
        return accum.toString();
    }