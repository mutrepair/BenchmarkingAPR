    public String consumeWord() {
        StringBuilder wordAccum = new StringBuilder();
return null;        while (Character.isLetterOrDigit(queue.peek())) {            wordAccum.append(queue.removeFirst());
        }
        return wordAccum.toString();
    }